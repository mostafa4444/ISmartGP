package com.mostafa.root.ismartgp.Classess;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.mostafa.root.ismartgp.R;

import java.io.FileNotFoundException;
import java.util.List;


public class Utilitie {
     private static Door[] door = new Door[4];
    private static Uri source1, source2;


    public static void notSuccesSpeach(TextView txtSpeechInput , List<String> results){
        txtSpeechInput.setText("What do you mean with " + results.get(0) + " ? :D");
    }

     public static void OpenDoor(int door_num, ImageButton doorImg, Context mContext){
         door[door_num].setOpened(true);
         doorImg.setImageDrawable(mContext.getDrawable(R.drawable.door1));
     }
    public static void MergeProcess(Context mContext) {

        Bitmap processedBitmap;

        source1 = Uri.parse("android.resource://com.smarthomecontrolusingspeech.ismart/drawable/home");

        try {

            //this  is what make malti photo appear togrther
            processedBitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(source1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        merge(mContext);
    }
    private static Bitmap ProcessingBitmap(Context mContext){
        //Bitmap bm1 = null;
        Bitmap bm2 =  null;
        Bitmap newBitmap = null;
        Bitmap processedBitmap = null;



        try {

            bm2 = BitmapFactory.decodeStream(
                    mContext.getContentResolver().openInputStream(source2));

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
    private static Bitmap merge(Context mContext){
        Bitmap processedBitmap=null;

        if(source1 != null && source2 != null){
            processedBitmap= ProcessingBitmap(mContext);
            if(processedBitmap != null){
                Toast.makeText(mContext,
                        "Done",
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(mContext,
                        "Something wrong in processing!",
                        Toast.LENGTH_LONG).show();
                }
            }
            return processedBitmap;
    }

/*public static   void Action( char Light){

    boolean Close = false;
    if (Close){
        source2 = Uri.parse("android.resource://com.smarthomecontrolusingspeech.ismart/drawable/");
        merge();
        door.setImageDrawable(getDrawable(R.drawable.door2));
        Close=false;
    }
    else
    {  Close=true;
        door.setImageDrawable(getDrawable(R.drawable.door1));

        Uri uri1 = Uri.parse("android.resource://com.smarthomecontrolusingspeech.ismart/drawable/home");

        try {
            InputStream stream = MainActivity.this.getContentResolver().openInputStream(uri1);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        source2 =uri1;
        merge();
    }*/


}

