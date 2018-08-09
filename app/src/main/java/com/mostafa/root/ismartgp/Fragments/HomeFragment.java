package com.mostafa.root.ismartgp.Fragments;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mostafa.root.ismartgp.Classess.DBUtils;
import com.mostafa.root.ismartgp.Classess.Utilitie;
import com.mostafa.root.ismartgp.MainActivity;
import com.mostafa.root.ismartgp.Model.HomeAction;
import com.mostafa.root.ismartgp.Model.HomeHelper;
import com.mostafa.root.ismartgp.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import uk.co.senab.photoview.PhotoViewAttacher;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class HomeFragment extends Fragment {
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView txtSpeechInput;
    AlertDialog alertDialog;
    String first;
    String second ;
    String third ;
    String current_action = "a";
    String next_action = "b";
    ArrayList<String> next_tree_action;
    ImageView pic;
    Bitmap processedBitmap;
    String state;
    private ImageView houseDoorImage, saloonLedImage, saloonTvImage, saloonFanImage, roomOneDoorImage, garageDoorImage,
            roomTwoDoorImage, pathroomDoorImage, roomTwoLedImage, roomOneLedImage, pathroomLedImage;
    private boolean isRoomOneLedOpened, isRoomTwoLedOpened, isPathLedOpened, isRoomOneDoorOpened, isRoomTwoDoorOpened,
            isPathDoorOpened, isHouseDoorOpened, isGarageDoorOpened, isSaloonLedOpened, isSaloonTvOpened, isSaloonFanOpened = false;
    DBUtils dbUtils;
    List<String> commandList;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private ImageView rooms[];
    private ImageView doors[];
    Uri source1, source2;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    final HashMap<String , String> myActionss = new HashMap<>();

    public HomeFragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        txtSpeechInput = rootView.findViewById(R.id.txt_speech_input);
        myActionss.put("a" , "Open House");
        myActionss.put("b" , "Close House");
        myActionss.put("c" , "Open Saloon Light");
        myActionss.put("d" , "Close Saloon Light");
        myActionss.put("e" , "Open Saloon Fan");
        myActionss.put("f" , "Close Saloon Fan");
        myActionss.put("g" , "Open Saloon TV");
        myActionss.put("h" , "Close Saloon TV");
        myActionss.put("i" , "Open Door Room One");
        myActionss.put("j" , "Close Door Room One");
        myActionss.put("k" , "Open Light Room One");
        myActionss.put("m" , "Close Light Room One");
        myActionss.put("n" , "Open Door Room Two");
        myActionss.put("o" , "Close Door Room Two");
        myActionss.put("p" , "Open Light Room Two");
        myActionss.put("q" , "Close Light Room Two");
        myActionss.put("r" , "Open Bathroom Door");
        myActionss.put("s" , "Close Bathroom Door");
        myActionss.put("t" , "Open Bathroom Light");
        myActionss.put("u" , "Close Bathroom Light");
        myActionss.put("v" , "Close Garage");
        myActionss.put("w" , "Open Garage");
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mPreferences.edit();
        address =mPreferences.getString("bluetooth" , "");
//        address = getActivity().getIntent().getStringExtra("bt");
        alertDialog =  new AlertDialog.Builder(getActivity()).create();

        next_tree_action = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        houseDoorImage = (ImageView) rootView.findViewById(R.id.house_door_imageView);
        saloonLedImage = (ImageView) rootView.findViewById(R.id.saloon_led_imageView);
        saloonTvImage = (ImageView) rootView.findViewById(R.id.saloon_tv_imageView);

        saloonFanImage = (ImageView) rootView.findViewById(R.id.saloon_fan_imageView);
        roomOneDoorImage = (ImageView) rootView.findViewById(R.id.room1_door_imageView);
        roomTwoDoorImage = (ImageView) rootView.findViewById(R.id.room2_door_imageView);
        pathroomDoorImage = (ImageView) rootView.findViewById(R.id.pathroom_door_imageView);
        roomOneLedImage = (ImageView) rootView.findViewById(R.id.room1_led_imageView);
        roomTwoLedImage = (ImageView) rootView.findViewById(R.id.room2_led_imageView);
        pathroomLedImage = (ImageView) rootView.findViewById(R.id.pathroom_led_imageView);
        garageDoorImage = (ImageView) rootView.findViewById(R.id.garage_door_imageview);
        myRef.child("Action").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myRef.child("Action").orderByChild("state").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                            String room = snapshot.getRef().getKey();
                            switch (room){
                                case "housedoor":
                                    String s = snapshot.child("state").getValue().toString();
                                    if(s.equals("true")){
                                        open_house_photo_change1();
                                    }
                                    break;
                                case "garagedoor":
                                    String s1 = snapshot.child("state").getValue().toString();
                                    if(s1.equals("true")){
                                        open_garage_photo_change1();
                                    }
                                    break;
                                case "roomonelight":
                                    String s2 = snapshot.child("state").getValue().toString();
                                    if(s2.equals("true")){
                                        open_room_one_light_photo_change1();
                                    }
                                    break;
                                case "roomonedoor":
                                    String s3 = snapshot.child("state").getValue().toString();
                                    if(s3.equals("true")){
                                        open_room_one_door_photo_change1();
                                    }
                                    break;
                                case "roomtwolight":
                                    String s4 = snapshot.child("state").getValue().toString();
                                    if(s4.equals("true")){
                                        open_room_two_light_photo_change1();
                                    }
                                    break;
                                case "roomtwodoor":
                                    String s5 = snapshot.child("state").getValue().toString();
                                    if(s5.equals("true")) {
                                        open_room_two_door_photo_change1();
                                    }
                                    break;
                                case "bathroomlight":
                                    String s6 = snapshot.child("state").getValue().toString();
                                    if(s6.equals("true")){
                                        open_bathroom_light_photo_change1();
                                    }
                                    break;
                                case "bathroomdoor":
                                    String s7 = snapshot.child("state").getValue().toString();
                                    if(s7.equals("true")){
                                        open_bathroom_door_photo_change1();
                                    }
                                    break;
                                case "saloonlight":
                                    String s8 = snapshot.child("state").getValue().toString();
                                    if(s8.equals("true")) {
                                        open_saloon_light_photo_change1();
                                    }
                                    break;
                                case "saloontv":
                                    String s9 = snapshot.child("state").getValue().toString();
                                    if(s9.equals("true")) {
                                        open_saloontv_photo_change1();
                                    }
                                    break;
                                case "saloonfan":
                                    String s10 = snapshot.child("state").getValue().toString();
                                    if(s10.equals("true")) {
                                        open_saloon_fan_photo_change1();
                                    }
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        houseDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isHouseDoorOpened) {
                    open_house_photo_change();
                } else {
                    close_house_photo_change();
                }
            }
        });
        saloonLedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSaloonLedOpened) {
                    open_saloon_light_photo_change();
                } else {
                    close_saloon_light_photo_change();
                }
            }
        });
        saloonTvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSaloonTvOpened) {
                    open_saloontv_photo_change();
                } else {
                    close_saloontv_photo_change();
                }
            }
        });
        saloonFanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSaloonFanOpened) {
                    open_saloon_fan_photo_change();
                } else {
                    close_saloon_fan_photo_change();
                }
            }
        });
        roomOneLedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomOneLedOpened) {
                    open_room_one_light_photo_change();
                } else {
                    close_room_one_light_photo_change();
                }
            }
        });
        roomOneDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomOneDoorOpened) {
                    open_room_one_door_photo_change();
                } else {
                    close_room_one_door_change();
                }
            }
        });
        roomTwoLedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomTwoLedOpened) {
                    open_room_two_light_photo_change();
                } else {
                    close_room_two_light_photo_change();
                }
            }
        });
        roomTwoDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomTwoDoorOpened) {
                    open_room_two_door_photo_change();
                } else {
                    close_room_two_door_change();
                }
            }
        });
        pathroomLedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPathLedOpened) {
                    open_bathroom_light_photo_change();
                } else {
                    close_bathroom_light_photo_change();
                }
            }
        });
        pathroomDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPathDoorOpened) {
                    open_bathroom_door_photo_change();
                } else {
                    close_bathroom_door_photo_change();
                }
            }
        });
        garageDoorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isGarageDoorOpened) {
                    open_garage_photo_change();
                } else {
                    close_garage_photo_change();
                }
            }
        });
        //-------------------------------------------------------------------------------------
        pic=(ImageView)rootView.findViewById(R.id.pic);
        PhotoViewAttacher photoview =new PhotoViewAttacher(pic);
        photoview.update();
        source1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/home");
        try {
            //this  is what make malti photo appear togrther
            processedBitmap = BitmapFactory.decodeStream(getActivity().getApplicationContext().getContentResolver().openInputStream(source1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        btnSpeak = (ImageButton) rootView.findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        new ConnectBT().execute();
        return rootView;
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(getActivity(), "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
    private void msg(String s)
    {
        Toast.makeText(getActivity() ,s,Toast.LENGTH_LONG).show();
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
    public String check_first_action(String command){
        int cont;
        int cc = 0;

        current_action = next_action;
        next_action = command;
        HomeHelper da = new HomeHelper(getActivity());
        List<HomeAction> home = da.getCOUNT(current_action , next_action);
        for (HomeAction c : home) {
            cont = (c.getAction_count());
            cont = cont+1;
            break;
        }
        List<HomeAction> home1 = da.getMaxCount(next_action);
        for (HomeAction c : home1) {
            int next = c.getAction_count();
            cc = next;
            break;
        }
        List<HomeAction> home3 = da.getExecptedNext(next_action , cc);
        for (HomeAction c : home3) {
            String actionNext = c.getNext_action();
            next_action = actionNext;
            break;
        }
        return next_action;
    }
    public String check_second_action(String command){
        int cont;
        int cc = 0;

        current_action = next_action;
        next_action = command;
        HomeHelper da = new HomeHelper(getActivity());
        List<HomeAction> home = da.getCOUNT(current_action , next_action);
        for (HomeAction c : home) {
            cont = (c.getAction_count());
            cont = cont+1;
            break;
        }
        List<HomeAction> home1 = da.getMaxCount(next_action);
        for (HomeAction c : home1) {
            int next = c.getAction_count();
            cc = next;
            break;
        }
        List<HomeAction> home3 = da.getExecptedNext(next_action , cc);
        for (HomeAction c : home3) {
            String actionNext = c.getNext_action();
            next_action = actionNext;
            break;
        }
        return next_action;
    }
    public String check_third_action(String command){
        int cont;
        int cc = 0;

        current_action = next_action;
        next_action = command;
        HomeHelper da = new HomeHelper(getActivity());
        List<HomeAction> home = da.getCOUNT(current_action , next_action);
        for (HomeAction c : home) {
            cont = (c.getAction_count());
            cont = cont+1;
            break;
        }
        List<HomeAction> home1 = da.getMaxCount(next_action);
        for (HomeAction c : home1) {
            int next = c.getAction_count();
            cc = next;
            break;
        }
        List<HomeAction> home3 = da.getExecptedNext(next_action , cc);
        for (HomeAction c : home3) {
            String actionNext = c.getNext_action();
            next_action = actionNext;
            break;
        }
        return next_action;
    }
    public void my_alert(final String one , final String two , final String three , final String current){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setIcon(R.drawable.think);
        builder1.setTitle("Let We Guess !!");
        builder1.setMessage("We Guess That Your Next Three Activities Will be "+myActionss.get(one)+" , " +
                myActionss.get(two) + " And "+myActionss.get(three) + " , Do you want to implement this actions in your house ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeHelper da = new HomeHelper(getActivity());
                        List<HomeAction> home = da.getCOUNT(current , one);
                        for (HomeAction c : home) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(current, one, cont);
                            checkDetectedStateIfWork(one);
                        }
                        List<HomeAction> home1 = da.getCOUNT(one , two);
                        for (HomeAction c : home1) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(one, two, cont);
                            checkDetectedStateIfWork(two);
                        }

                        List<HomeAction> home2 = da.getCOUNT(two , three);
                        for (HomeAction c : home2) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(two, three, cont);
                            current_action = three;
                            checkDetectedStateIfWork(three);

                        }
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeHelper da = new HomeHelper(getActivity());
                        List<HomeAction> home2 = da.getCOUNT(current_action , current);
                        for (HomeAction c : home2) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(current_action, current, cont);
                            current_action = current;
                        }
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void my_alert_for_one(final String one , final String current){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setIcon(R.drawable.think);
        builder1.setTitle("Let We Guess !!");
        builder1.setMessage("We Guess That Your Next Action Will be "+myActionss.get(one)+" , Do you want to implement this actions in your house ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeHelper da = new HomeHelper(getActivity());
                        List<HomeAction> home = da.getCOUNT(current , one);
                        for (HomeAction c : home) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(current, one, cont);
                            checkDetectedStateIfWork(one);
                        }
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeHelper da = new HomeHelper(getActivity());
                        List<HomeAction> home2 = da.getCOUNT(current_action , current);
                        for (HomeAction c : home2) {
                            int cont;
                            cont = (c.getAction_count());
                            cont = cont + 1;
                            da.updataSingleAction(current_action, current, cont);
                            current_action = current;
                        }
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void open_house_photo_change(){
        houseDoorImage.setImageResource(R.drawable.openeddoorhouse);
        myRef.child("Action").child("housedoor").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_door_1");

        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 =uri1;
        merge();
        isHouseDoorOpened = true;
        String current = "a";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("a");
                my_alert_for_one(first_action , "a");
                break;
            case "Three":
                sendToArduino("a");
                my_alert(first_action , second_action , third_action , "a");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "a";
                List<HomeAction> home2 = da.getCOUNT(current_action , "a");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "a", cont);
                    current_action = "a";
                    sendToArduino("a");
                }
                break;
        }
    }
    public void close_house_photo_change(){
        houseDoorImage.setImageResource(R.drawable.closeddoorhouse);
        myRef.child("Action").child("housedoor").child("state").setValue("false");
        isHouseDoorOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_door_1");

        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 =uri1;
        merge();
        String current = "b";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("b");
                my_alert_for_one(first_action , "b");
                break;
            case "Three":
                sendToArduino("b");
                my_alert(first_action , second_action , third_action , "b");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "b";
                List<HomeAction> home2 = da.getCOUNT(current_action , "b");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "b", cont);
                    current_action = "b";
                    sendToArduino("b");

                }
                break;
        }
    }
    public void open_saloon_light_photo_change(){
        saloonLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("saloonlight").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_light_room1");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        isSaloonLedOpened = true;
        String current = "c";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("c");
                my_alert_for_one(first_action , "c");
                break;
            case "Three":
                sendToArduino("c");
                my_alert(first_action , second_action , third_action , "c");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "c";
                List<HomeAction> home2 = da.getCOUNT(current_action , "c");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "c", cont);
                    current_action = "c";
                    sendToArduino("c");
                }
                break;
        }
    }
    public void close_saloon_light_photo_change(){
        saloonLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("saloonlight").child("state").setValue("false");
        isSaloonLedOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_light_room1");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "d";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("d");
                my_alert_for_one(first_action , "d");
                break;
            case "Three":
                sendToArduino("d");
                my_alert(first_action , second_action , third_action , "d");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "d";
                List<HomeAction> home2 = da.getCOUNT(current_action , "d");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "d", cont);
                    current_action = "d";
                    sendToArduino("d");
                }
                break;
        }    }
    public void open_saloontv_photo_change(){
        saloonTvImage.setImageResource(R.drawable.openedtv);
        myRef.child("Action").child("saloontv").child("state").setValue("true");
        isSaloonTvOpened = true;
        String current = "g";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("g");
                my_alert_for_one(first_action , "g");
                break;
            case "Three":
                sendToArduino("g");
                my_alert(first_action , second_action , third_action , "g");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "g";
                List<HomeAction> home2 = da.getCOUNT(current_action , "g");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "g", cont);
                    current_action = "g";
                    sendToArduino("g");

                }
                break;
        }    }
    public void close_saloontv_photo_change(){
        saloonTvImage.setImageResource(R.drawable.closedtv);
        myRef.child("Action").child("saloontv").child("state").setValue("false");
        isSaloonTvOpened = false;
        String current = "h";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("h");
                my_alert_for_one(first_action , "h");
                break;
            case "Three":
                sendToArduino("h");
                my_alert(first_action , second_action , third_action , "h");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "h";
                List<HomeAction> home2 = da.getCOUNT(current_action , "h");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "h", cont);
                    current_action = "h";
                    sendToArduino("h");
                }
                break;
        }    }
    public void open_saloon_fan_photo_change(){
        saloonFanImage.setImageResource(R.drawable.openedfan);
        myRef.child("Action").child("saloonfan").child("state").setValue("true");
        isSaloonFanOpened = true;
        String current = "e";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("e");
                my_alert_for_one(first_action , "e");
                break;
            case "Three":
                sendToArduino("e");
                my_alert(first_action , second_action , third_action , "e");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "e";
                List<HomeAction> home2 = da.getCOUNT(current_action , "e");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "e", cont);
                    current_action = "e";
                    sendToArduino("e");
                }
                break;
        }    }
    public void close_saloon_fan_photo_change(){
        saloonFanImage.setImageResource(R.drawable.closedfan);
        myRef.child("Action").child("saloonfan").child("state").setValue("false");
        isSaloonFanOpened = false;
        String current = "f";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("f");
                my_alert_for_one(first_action , "f");
                break;
            case "Three":
                sendToArduino("f");
                my_alert(first_action , second_action , third_action , "f");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "f";
                List<HomeAction> home2 = da.getCOUNT(current_action , "f");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "f", cont);
                    current_action = "f";
                    sendToArduino("f");
                }
                break;
        }    }
    public void open_room_one_door_photo_change(){
        roomOneDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("roomonedoor").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_door_2");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        isRoomOneDoorOpened = true;
        String current = "i";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("i");
                my_alert_for_one(first_action , "i");
                break;
            case "Three":
                sendToArduino("i");
                my_alert(first_action , second_action , third_action , "i");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "i";
                List<HomeAction> home2 = da.getCOUNT(current_action , "i");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "i", cont);
                    current_action = "i";
                    sendToArduino("i");
                }
                break;
        }    }
    public void close_room_one_door_change(){
        roomOneDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("roomonedoor").child("state").setValue("false");
        isRoomOneDoorOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_door_2");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "j";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("j");
                my_alert_for_one(first_action , "j");
                break;
            case "Three":
                sendToArduino("j");
                my_alert(first_action , second_action , third_action , "j");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "j";
                List<HomeAction> home2 = da.getCOUNT(current_action , "j");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "j", cont);
                    current_action = "j";
                    sendToArduino("j");
                }
                break;
        }    }
    public void open_room_one_light_photo_change(){
        roomOneLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("roomonelight").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.smarthomecontrolusingspeech.ismart/drawable/open_light_room2");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        isRoomOneLedOpened = true;
        String current = "k";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("k");
                my_alert_for_one(first_action , "k");
                break;
            case "Three":
                sendToArduino("k");
                my_alert(first_action , second_action , third_action , "k");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "k";
                List<HomeAction> home2 = da.getCOUNT(current_action , "k");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "k", cont);
                    current_action = "k";
                    sendToArduino("k");

                }
                break;
        }    }
    public void close_room_one_light_photo_change(){
        roomOneLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("roomonelight").child("state").setValue("false");
        isRoomOneLedOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_light_room2");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "m";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("m");
                my_alert_for_one(first_action , "m");
                break;
            case "Three":
                sendToArduino("m");
                my_alert(first_action , second_action , third_action , "m");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "m";
                List<HomeAction> home2 = da.getCOUNT(current_action , "m");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "m", cont);
                    current_action = "m";
                    sendToArduino("m");

                }
                break;
        }    }
    public void open_room_two_door_photo_change(){
        roomTwoDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("roomtwodoor").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_door_3");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        isRoomTwoDoorOpened = true;
        String current = "n";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("n");
                my_alert_for_one(first_action , "n");
                break;
            case "Three":
                sendToArduino("n");
                my_alert(first_action , second_action , third_action , "n");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "n";
                List<HomeAction> home2 = da.getCOUNT(current_action , "n");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "n", cont);
                    current_action = "n";
                    sendToArduino("n");

                }
                break;
        }    }
    public void close_room_two_door_change(){
        roomTwoDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("roomtwodoor").child("state").setValue("false");
        isRoomTwoDoorOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_door_3");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "o";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("o");
                my_alert_for_one(first_action , "o");
                break;
            case "Three":
                sendToArduino("o");
                my_alert(first_action , second_action , third_action , "o");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "o";
                List<HomeAction> home2 = da.getCOUNT(current_action , "o");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "o", cont);
                    current_action = "o";
                    sendToArduino("o");
                }
                break;
        }    }
    public void open_room_two_light_photo_change(){
        roomTwoLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("roomtwolight").child("state").setValue("true");
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_light_room3");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        isRoomTwoLedOpened = true;
        String current = "p";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("p");
                my_alert_for_one(first_action , "p");
                break;
            case "Three":
                sendToArduino("p");
                my_alert(first_action , second_action , third_action , "p");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "p";
                List<HomeAction> home2 = da.getCOUNT(current_action , "p");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "p", cont);
                    current_action = "p";
                    sendToArduino("p");
                }
                break;
        }    }
    public void close_room_two_light_photo_change(){
        roomTwoLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("roomtwolight").child("state").setValue("false");
        isRoomTwoLedOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_light_room3");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "q";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("q");
                my_alert_for_one(first_action , "q");
                break;
            case "Three":
                sendToArduino("q");
                my_alert(first_action , second_action , third_action , "q");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "q";
                List<HomeAction> home2 = da.getCOUNT(current_action , "q");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "q", cont);
                    current_action = "q";
                    sendToArduino("q");
                }
                break;
        }    }
    public void open_bathroom_door_photo_change(){
        pathroomDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("bathroomdoor").child("state").setValue("true");
        Uri uri2 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_door_4");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri2;
        merge();
        isPathDoorOpened = true;
        String current = "r";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("r");
                my_alert_for_one(first_action , "r");
                break;
            case "Three":
                sendToArduino("r");
                my_alert(first_action , second_action , third_action , "r");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "r";
                List<HomeAction> home2 = da.getCOUNT(current_action , "r");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "r", cont);
                    current_action = "r";
                    sendToArduino("r");
                }
                break;
        }    }
    public void open_bathroom_light_photo_change(){
        pathroomLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("bathroomlight").child("state").setValue("true");
        Uri uri2 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/open_light_room4");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri2);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri2;
        merge();
        isPathLedOpened = true;
        String current = "t";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("t");
                my_alert_for_one(first_action , "t");
                break;
            case "Three":
                sendToArduino("t");
                my_alert(first_action , second_action , third_action , "t");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "t";
                List<HomeAction> home2 = da.getCOUNT(current_action , "t");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "t", cont);
                    current_action = "t";
                    sendToArduino("t");
                }
                break;
        }    }
    public void close_bathroom_door_photo_change(){
        pathroomDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("bathroomdoor").child("state").setValue("false");
        isPathDoorOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_door_4");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "s";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("s");
                my_alert_for_one(first_action , "s");
                break;
            case "Three":
                sendToArduino("s");
                my_alert(first_action , second_action , third_action , "s");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "s";
                List<HomeAction> home2 = da.getCOUNT(current_action , "s");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "s", cont);
                    current_action = "s";
                    sendToArduino("s");
                }
                break;
        }    }
    public void close_bathroom_light_photo_change(){
        pathroomLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("bathroomlight").child("state").setValue("false");
        isPathLedOpened = false;
        Uri uri1 = Uri.parse("android.resource://com.mostafa.root.ismartgp/drawable/close_light_room4");
        try {
            InputStream stream = getActivity().getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 = uri1;
        merge();
        String current = "u";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("u");
                my_alert_for_one(first_action , "u");
                break;
            case "Three":
                sendToArduino("u");
                my_alert(first_action , second_action , third_action , "u");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "u";
                List<HomeAction> home2 = da.getCOUNT(current_action , "u");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "u", cont);
                    current_action = "u";
                    sendToArduino("u");
                }
                break;
        }    }
    public void open_garage_photo_change(){
        garageDoorImage.setImageResource(R.drawable.openedgrage);
        myRef.child("Action").child("garagedoor").child("state").setValue("true");
        isGarageDoorOpened = true;
        String current = "w";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("w");
                my_alert_for_one(first_action , "w");
                break;
            case "Three":
                sendToArduino("w");
                my_alert(first_action , second_action , third_action , "w");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "w";
                List<HomeAction> home2 = da.getCOUNT(current_action , "w");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "w", cont);
                    current_action = "w";
                    sendToArduino("w");
                }
                break;
        }    }
    public void close_garage_photo_change(){
        garageDoorImage.setImageResource(R.drawable.closedgrage);
        myRef.child("Action").child("garagedoor").child("state").setValue("false");
        isGarageDoorOpened = false;
        String current = "v";
        String first_action = check_first_action(current);
        String second_action = check_second_action(first_action);
        String third_action = check_third_action(second_action);
        String number_detected = mPreferences.getString("detect_action" , "");
        switch (number_detected){
            case "One":
                sendToArduino("v");
                my_alert_for_one(first_action , "v");
                break;
            case "Three":
                sendToArduino("v");
                my_alert(first_action , second_action , third_action , "v");
                break;
            case "None":
                HomeHelper da = new HomeHelper(getActivity());
                next_action = "v";
                List<HomeAction> home2 = da.getCOUNT(current_action , "v");
                for (HomeAction c : home2) {
                    int cont;
                    cont = (c.getAction_count());
                    cont = cont + 1;
                    da.updataSingleAction(current_action, "v", cont);
                    current_action = "v";
                    sendToArduino("v");
                }
                break;
        }    }
    //-----------------------------------------------------------------------------------------------------------------------
    public void open_house_photo_change1(){
        houseDoorImage.setImageResource(R.drawable.openeddoorhouse);
        myRef.child("Action").child("housedoor").child("state").setValue("true");
        isHouseDoorOpened = true;
    }
    public void open_saloon_light_photo_change1(){
        saloonLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("saloonlight").child("state").setValue("true");
        isSaloonLedOpened = true;
    }
    public void open_saloontv_photo_change1(){
        saloonTvImage.setImageResource(R.drawable.openedtv);
        myRef.child("Action").child("saloontv").child("state").setValue("true");
        isSaloonTvOpened = true;
    }
    public void open_saloon_fan_photo_change1(){
        saloonFanImage.setImageResource(R.drawable.openedfan);
        myRef.child("Action").child("saloonfan").child("state").setValue("true");
        isSaloonFanOpened = true;
    }
    public void open_room_one_door_photo_change1(){
        roomOneDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("roomonedoor").child("state").setValue("true");
        isRoomOneDoorOpened = true;
    }
    public void open_room_one_light_photo_change1(){
        roomOneLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("roomonelight").child("state").setValue("true");
        isRoomOneLedOpened = true;

    }
    public void open_room_two_door_photo_change1(){
        roomTwoDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("roomtwodoor").child("state").setValue("true");
        isRoomTwoDoorOpened = true;

    }
    public void open_room_two_light_photo_change1(){
        roomTwoLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("roomtwolight").child("state").setValue("true");
        isRoomTwoLedOpened = true;
    }
    public void open_bathroom_door_photo_change1(){
        pathroomDoorImage.setImageResource(R.drawable.openedroomdoor);
        myRef.child("Action").child("bathroomdoor").child("state").setValue("true");
        isPathDoorOpened = true;

    }
    public void open_bathroom_light_photo_change1(){
        pathroomLedImage.setImageResource(R.drawable.openedled);
        myRef.child("Action").child("bathroomlight").child("state").setValue("true");
        isPathLedOpened = true;
    }
    public void open_garage_photo_change1(){
        garageDoorImage.setImageResource(R.drawable.openedgrage);
        myRef.child("Action").child("garagedoor").child("state").setValue("true");
        isGarageDoorOpened = true;
    }
    public void close_house_photo_change1() {
        houseDoorImage.setImageResource(R.drawable.closeddoorhouse);
        myRef.child("Action").child("housedoor").child("state").setValue("false");
        isHouseDoorOpened = false;
    }
    public void close_saloon_light_photo_change1(){
        saloonLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("saloonlight").child("state").setValue("false");
        isSaloonLedOpened = false;
    }
    public void close_saloontv_photo_change1(){
        saloonTvImage.setImageResource(R.drawable.closedtv);
        myRef.child("Action").child("saloontv").child("state").setValue("false");
        isSaloonTvOpened = false;
    }
    public void close_saloon_fan_photo_change1(){
        saloonFanImage.setImageResource(R.drawable.closedfan);
        myRef.child("Action").child("saloonfan").child("state").setValue("false");
        isSaloonFanOpened = false;
    }
    public void close_room_one_door_photo_change1(){
        roomOneDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("roomonedoor").child("state").setValue("false");
        isRoomOneDoorOpened = false;
    }
    public void close_room_one_light_photo_change1(){
        roomOneLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("roomonelight").child("state").setValue("false");
        isRoomOneLedOpened = false;
    }
    public void close_room_two_door_photo_change1(){
        roomTwoDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("roomtwodoor").child("state").setValue("false");
        isRoomTwoDoorOpened = false;
    }
    public void close_room_two_light_photo_change1(){
        roomTwoLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("roomtwolight").child("state").setValue("false");
        isRoomTwoLedOpened = false;
    }
    public void close_bathroom_door_photo_change1(){
        pathroomDoorImage.setImageResource(R.drawable.closedroomdoor);
        myRef.child("Action").child("bathroomdoor").child("state").setValue("false");
        isPathDoorOpened = false;
    }
    public void close_bathroom_light_photo_change1(){
        pathroomLedImage.setImageResource(R.drawable.closedled);
        myRef.child("Action").child("bathroomlight").child("state").setValue("false");
        isPathLedOpened = false;
    }
    public void close_garage_photo_change1(){
        garageDoorImage.setImageResource(R.drawable.closedgrage);
        myRef.child("Action").child("garagedoor").child("state").setValue("false");
        isGarageDoorOpened = false;
    }
    //----------------------------------------------------------------------------------------------------------------------------
    private void merge() {
        if (source1 != null && source2 != null) {
            processedBitmap = ProcessingBitmap();
            if (processedBitmap != null) {
                pic.setImageBitmap(processedBitmap);

            } else {
                Toast.makeText(getActivity(),
                        "Something wrong in processing!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    private Bitmap ProcessingBitmap(){
        Bitmap bm2 =  null;
        Bitmap newBitmap = null;

        try {
            bm2 = BitmapFactory.decodeStream(
                    getActivity().getContentResolver().openInputStream(source2));

            int w;
            if(processedBitmap.getWidth() >= bm2.getWidth()){
                w = processedBitmap.getWidth();
            }else{
                w = bm2.getWidth();
            }

            int h;
            if(processedBitmap.getHeight() >= bm2.getHeight()){
                h = processedBitmap.getHeight();
            }else{
                h = bm2.getHeight();
            }

            Bitmap.Config config = processedBitmap.getConfig();
            if(config == null){
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(w, h, config);
            Canvas newCanvas = new Canvas(newBitmap);

            newCanvas.drawBitmap(processedBitmap, 0, 0, null);

            Paint paint = new Paint();
            paint.setAlpha(250);
            newCanvas.drawBitmap(bm2, 0, 0, paint);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.e("door1",e.getMessage());
            e.printStackTrace();
        }

        return newBitmap;
    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        commandList = new ArrayList<>();
        commandList.add("turn on light");
        commandList.add("switch on light");
        commandList.add("turn on lights");
        commandList.add("turn on the light");
        commandList.add("turn on the lights");
        commandList.add("switch on the lights");
        commandList.add("open light");
        commandList.add("led on");
        commandList.add("open lights");
        commandList.add("open the light");
        commandList.add("open the lights");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String r : result) {
                        for (String s : commandList) {
                            if (s.length() < r.length()) {
                                if (s.equalsIgnoreCase(r.substring(0, s.length()))) {
                                    txtSpeechInput.setText("turn on light");
                                    String room = r.replaceAll(s, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
//                                        rooms[room_num].setImageDrawable(getDrawable(R.drawable.gold_image));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        switch (room_num.toLowerCase()) {

                                            case "two":
                                            case "too":
                                            case "to":
                                            case "2":

                                                open_room_two_light_photo_change();
                                                break;


                                            case "one":
                                            case "on":
                                            case "1":
                                                open_room_one_light_photo_change();
                                                break;
                                        }
                                    }
                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
                    if (!success) {
                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
                    }
                }

            }
        }


        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList2;
        commandList2 = new ArrayList<>();
        commandList2.add("turn off light");
        commandList2.add("turn of light");
        commandList2.add("close light");
        commandList2.add("switch off light");
        commandList2.add("turn off lights");
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String b : result) {
                        for (String v : commandList2) {
                            if (v.length() < b.length()) {
                                if (v.equalsIgnoreCase(b.substring(0, v.length()))) {
                                    txtSpeechInput.setText("turn off light");
                                    String room = b.replaceAll(v, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                        // rooms[room_num].setImageDrawable(getDrawable(R.drawable.gold_image));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        switch (room_num.toLowerCase()) {
                                            case "one":
                                            case "on":
                                            case "1":
                                                close_room_one_light_photo_change();
                                                break;
                                            case "two":
                                            case "too":
                                            case "to":
                                            case "2":
                                                close_room_two_light_photo_change();
                                                break;
                                        }
                                    }
                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
                    if (!success) {
                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
                    }
                }

            }
        }

//---------------------------------------------------------------------------------------------

        super.onActivityResult(requestCode, resultCode, data);
        ArrayList <String> commandOpenDoors = new ArrayList<>();
        commandOpenDoors.add("open door");
        commandOpenDoors.add("open the door");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String q : result) {
                        for (String w : commandOpenDoors) {
                            if (w.length() < q.length()) {
                                if (w.equalsIgnoreCase(q.substring(0, w.length()))) {
                                    txtSpeechInput.setText("open door");
                                    String room = q.replaceAll(w, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        switch (room_num.toLowerCase()) {


                                            case "two":
                                            case "too":
                                            case "to":
                                            case "2":

                                                open_room_two_door_photo_change();
                                                break;

                                            case "one":
                                            case "on":
                                            case "1":
                                                open_room_one_door_photo_change();
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }

            }
        }

        //-----------------------------------------------------------------------------------

        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList5 = new ArrayList<>();
        commandList5.add("open");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList5) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("open");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {
                                            case "house":
                                            case "home":
                                                if (isHouseDoorOpened) {
                                                    Toast.makeText(getActivity(), "The House Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_house_photo_change();
                                                }
                                                break;

                                            case "garage":
                                            case "golf":
                                                if (!isGarageDoorOpened) {
                                                    open_garage_photo_change();
                                                } else {
                                                    Toast.makeText(getActivity(), "The garage Already Opened ", Toast.LENGTH_SHORT).show();
                                                }
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }


        //*********************************************************************************************************************************************

        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList6 = new ArrayList<>();
        commandList6.add("open bathroom");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList6) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("open bathroom ?");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {
                                            case "led":
                                            case "light":
                                            case "blind":
                                                if (isPathLedOpened) {
                                                    Toast.makeText(getActivity(), "The Pathroom light Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_bathroom_light_photo_change();
                                                }
                                                break;

                                            case "door":
                                                if (isPathDoorOpened) {
                                                    Toast.makeText(getActivity(), "The Path Door Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_bathroom_door_photo_change();
                                                }
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }


        //**************************************************************************************************************************************************

        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList8 = new ArrayList<>();
        commandList8.add("open saloon");
        commandList8.add("open salon");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList8) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("open saloon");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {
                                            case "led":
                                            case "light":
                                                if (isSaloonLedOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon light Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_saloon_light_photo_change();
                                                }
                                                break;
                                            case "television":
                                            case "tv":
                                                if (isSaloonTvOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon tv Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_saloontv_photo_change();
                                                }
                                                break;
                                            case "fan":
                                            case "fun":
                                            case "van":
                                            case "farm":
                                            case "bhan":
                                                if (isSaloonFanOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon fan Already Opened ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    open_saloon_fan_photo_change();
                                                }
                                                break;

                                        }
                                    }
                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }

        //******************************************************************************************************************************************
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList7 = new ArrayList<>();
        commandList7.add("close bathroom");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList7) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("close bathroom ?");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {

                                            case "door":
                                                if (!isPathDoorOpened) {
                                                    Toast.makeText(getActivity(), "The bathroom door Already Closed ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    close_bathroom_door_photo_change();
                                                }
                                                break;

                                            case "led":
                                            case "light":
                                                if (!isPathLedOpened) {
                                                    Toast.makeText(getActivity(), "The bathroom light Already Closed ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    close_bathroom_light_photo_change();
                                                }
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }

        //*****************************************************************************************************************************************************
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList9 = new ArrayList<>();
        commandList9.add("close salon");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList9) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("close saloon ?");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {
                                            case "light":
                                            case "lite":
                                            case "leeds":
                                            case "led":
                                                if (!isSaloonLedOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon light Already Closed ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    close_saloon_light_photo_change();
                                                }
                                                break;
                                            case "tv":
                                            case "television":
                                                if (!isSaloonTvOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon tv Already Closed ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    close_saloontv_photo_change();
                                                }
                                                break;
                                            case "fan":
                                            case "fun":
                                            case "Bahn":
                                                if (!isSaloonFanOpened) {
                                                    Toast.makeText(getActivity(), "The Saloon fan Already Closed ", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    close_saloon_fan_photo_change();
                                                }
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }

        //****************************************************************************************************************************************
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList12 = new ArrayList<>();
        commandList12.add("close");
        commandList12.add("lock");


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList12) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("close ?");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        switch (room_num.toLowerCase()) {
                                            case "house":
                                            case "home":
                                                if (isHouseDoorOpened) {
                                                    close_house_photo_change();
                                                } else {
                                                    Toast.makeText(getActivity(),  "The House Already Closed ", Toast.LENGTH_SHORT).show();
                                                }
                                                break;
                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }
        //***************************************************************************************************************************
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> commandList11 = new ArrayList<>();
        commandList11.add("close");
        commandList11.add("lock");
        commandList11.add("close");


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : commandList11) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("close ?");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        switch (room_num.toLowerCase()) {

                                            case "golf":
                                            case "gosh":
                                            case "garage":
                                                if (isGarageDoorOpened) {
                                                    close_garage_photo_change();
                                                } else {
                                                    Toast.makeText(getActivity() , "The garage Already Closed ", Toast.LENGTH_SHORT).show();
                                                }
                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success) {
                            break;
                        }
//                        if (!success) {
//                            Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                        }
                    }

                }
                break;
            }
        }


        //------------------------------------------------------------------------


        super.onActivityResult(requestCode, resultCode, data);
        ArrayList <String> command_close_doors = new ArrayList<>();
        command_close_doors.add("close door");
        command_close_doors.add("close the door");

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    boolean success = false;
                    for (String i : result) {
                        for (String b : command_close_doors) {
                            if (b.length() < i.length()) {
                                if (b.equalsIgnoreCase(i.substring(0, b.length()))) {
                                    txtSpeechInput.setText("close door");
                                    String room = i.replaceAll(b, "").trim();
                                    try {
                                        int room_num = Integer.parseInt(String.valueOf(room.charAt(room.length() - 1)));
                                    } catch (Exception e) {
                                        String[] room_split = room.split(" ");
                                        String room_num = room_split[room_split.length - 1];
                                        //we didn't finish the
                                        switch (room_num.toLowerCase()) {
                                            case "one":
                                            case "on":
                                            case "1":
                                                close_room_one_door_change();
                                                break;
                                            case "two":
                                            case "too":
                                            case "to":
                                            case "2":
                                                close_room_two_door_change();
                                                break;

                                        }
                                    }
//                                    txtSpeechInput.setText(txtSpeechInput.getText().toString() + ",your room is " + room);
                                    success = true;
                                    break;
                                }
                            }
                        }
                        if (success)
                            break;
                    }
//                    if (!success) {
//                        Utilitie.notSuccesSpeach(txtSpeechInput, result);
//                    }
                }
                break;
            }
        }




    }
    public void change_dependent_detect_actions(String action) {
        switch (action) {
            case "a":
                open_house_photo_change1();
                break;
            case "b":
                close_house_photo_change1();
                break;
            case "c":
                open_saloon_light_photo_change1();
                break;
            case "d":
                close_saloon_light_photo_change1();
                break;
            case "e":
                open_saloon_fan_photo_change1();
                break;
            case "f":
                close_saloon_fan_photo_change1();
                break;
            case "g":
                open_saloontv_photo_change1();
                break;
            case "h":
                close_saloontv_photo_change1();
                break;
            case "i":
                open_room_one_door_photo_change1();
                break;
            case "j":
                close_room_one_door_photo_change1();
                break;
            case "k":
                open_room_one_light_photo_change1();
                break;
            case "m":
                close_room_one_light_photo_change1();
                break;
            case "n":
                open_room_two_door_photo_change1();
                break;
            case "o":
                close_room_two_door_photo_change1();
                break;
            case "p":
                open_room_two_light_photo_change1();
                break;
            case "q":
                close_room_two_light_photo_change1();
                break;
            case "r":
                open_bathroom_door_photo_change1();
                break;
            case "s":
                close_bathroom_door_photo_change1();
                break;
            case "t":
                open_bathroom_light_photo_change1();
                break;
            case "u":
                close_bathroom_light_photo_change1();
                break;
            case "w":
                open_garage_photo_change1();
                break;
            case "v":
                close_garage_photo_change1();
                break;
        }
    }
    private void sendToArduino(String query){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write(query.toString().getBytes());
                Toast.makeText(getActivity() , "You send "+query , Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }
    public void checkDetectedStateIfWork(String action_num){
        switch (action_num){
            case "a": // open house
                if(isHouseDoorOpened){
                    Toast.makeText(getActivity() , "The House Door is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("a");
                    change_dependent_detect_actions("a");
                }
                break;
            case "b": //close house
                if(!isHouseDoorOpened){
                    Toast.makeText(getActivity() , "The House Door is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("b");
                    change_dependent_detect_actions("b");
                }
                break;
            case "c": //saloon light on
                if(isSaloonLedOpened){
                    Toast.makeText(getActivity() , "The Saloon Light is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("c");
                    change_dependent_detect_actions("c");
                }
                break;
            case "d": // saloon light off
                if(!isSaloonLedOpened){
                    Toast.makeText(getActivity() , "The Saloon Light is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("d");
                    change_dependent_detect_actions("d");
                }
                break;
            case "e": // saloon fan On
                if(isSaloonFanOpened){
                    Toast.makeText(getActivity() , "The Saloon Fan is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("e");
                    change_dependent_detect_actions("e");
                }
                break;
            case "f": // saloon fan Off
                if(!isSaloonFanOpened){
                    Toast.makeText(getActivity() , "The Saloon Fan is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("f");
                    change_dependent_detect_actions("f");
                }
                break;
            case "g": // saloon tv ON
                if(isSaloonTvOpened){
                    Toast.makeText(getActivity() , "The Saloon TV is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("g");
                    change_dependent_detect_actions("g");
                }
                break;
            case "h": // Saloon tv Off
                if(!isSaloonTvOpened){
                    Toast.makeText(getActivity() , "The Saloon TV is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("h");
                    change_dependent_detect_actions("h");
                }
                break;
            case "i": // room one Open
                if(isRoomOneDoorOpened){
                    Toast.makeText(getActivity() , "The Room 1 Door is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("i");
                    change_dependent_detect_actions("i");
                }
                break;
            case "j": // room one close
                if(!isRoomOneDoorOpened){
                    Toast.makeText(getActivity() , "The Room 1 Door is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("j");
                    change_dependent_detect_actions("j");
                }
                break;
            case "k": // room one light On
                if(isRoomOneLedOpened){
                    Toast.makeText(getActivity() , "The Room 1 Light is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("k");
                    change_dependent_detect_actions("k");
                }
                break;
            case "m":  // room one light Off
                if(!isRoomOneLedOpened){
                    Toast.makeText(getActivity() , "The Room 1 Light is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("m");
                    change_dependent_detect_actions("m");
                }
                break;
            case "n": // room two open
                if(isRoomTwoDoorOpened){
                    Toast.makeText(getActivity() , "The Room 2 Door is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("n");
                    change_dependent_detect_actions("n");
                }
                break;
            case "o": // room two close
                if(!isRoomTwoDoorOpened){
                    Toast.makeText(getActivity() , "The Room 2 Door is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("o");
                    change_dependent_detect_actions("o");
                }
                break;
            case "p":  // room two light On
                if(isRoomTwoLedOpened){
                    Toast.makeText(getActivity() , "The Room 2 Light is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("p");
                    change_dependent_detect_actions("p");
                }
                break;
            case "q":  // room one light Off
                if(!isRoomTwoLedOpened){
                    Toast.makeText(getActivity() , "The Room 2 Light is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("q");
                    change_dependent_detect_actions("q");
                }
                break;
            case "r":  // bathroom opened
                if(isPathDoorOpened){
                    Toast.makeText(getActivity() , "The Bathroom Door is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("r");
                    change_dependent_detect_actions("r");
                }
                break;
            case "s": // bathroom closed
                if(!isPathDoorOpened){
                    Toast.makeText(getActivity() , "The Bathroom Door is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("s");
                    change_dependent_detect_actions("s");
                }
                break;
            case "t":  // bathroom light On
                if(isPathLedOpened){
                    Toast.makeText(getActivity() , "The Bathroom Light is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("t");
                    change_dependent_detect_actions("t");
                }
                break;
            case "u":  // bathroom light Off
                if(!isPathLedOpened){
                    Toast.makeText(getActivity() , "The Bathroom Light is Already Closed" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("u");
                    change_dependent_detect_actions("u");
                }
                break;
            case "v": // garage Open
                if(isGarageDoorOpened){
                    Toast.makeText(getActivity() , "The Garage is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("w");
                    change_dependent_detect_actions("w");
                }
                break;
            case "w": // garage CLose
                if(!isGarageDoorOpened){
                    Toast.makeText(getActivity() , "The Garage is Already Opened" , Toast.LENGTH_SHORT).show();
                }else{
                    sendToArduino("v");
                    change_dependent_detect_actions("v");
                }
                break;
        }
    }
}