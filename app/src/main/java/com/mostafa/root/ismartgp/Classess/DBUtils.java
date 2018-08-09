package com.mostafa.root.ismartgp.Classess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by AhmedEltoukhy on 30-Oct-17.
 */

public class DBUtils extends SQLiteOpenHelper {
    private String TAG = DBUtils.class.getSimpleName();

    private static final String DATABASE_NAME = "iSmart.db";
    private static final int DATABASE_VERSION = 1;

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CommandsContract.CommandsEntity.SQL_QUERY_CREATE);
        db.execSQL(OrderContract.OrderEntity.SQL_QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CommandsContract.CommandsEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OrderContract.OrderEntity.TABLE_NAME);
        onCreate(db);
    }

    public void insertCommand(String commandTitle, int orederId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(CommandsContract.CommandsEntity.COLUMN_ORDER_ID, orederId);
        contentValues.put(CommandsContract.CommandsEntity.COLUMN_COMMAND, commandTitle);
        Log.e("Commands", commandTitle);

        db.insert(CommandsContract.CommandsEntity.TABLE_NAME, null, contentValues);
    }

    public void insertOrder(String orderTitle, String desc, int orderId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(OrderContract.OrderEntity.COLUMN_ORDER, orderId);
        contentValues.put(OrderContract.OrderEntity.COLUMN_ORDER, orderTitle);
        contentValues.put(OrderContract.OrderEntity.COLUMN_DESCRIPTION, desc);

        db.insert(OrderContract.OrderEntity.TABLE_NAME, null, contentValues);
    }

    public Command getSingleCommand(int commandId){

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CommandsContract.CommandsEntity.TABLE_NAME + " WHERE "
                + CommandsContract.CommandsEntity.COLUMN_COMMAND_ID + " = " + commandId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Command command = new Command();
        command.setCommand_ID(Integer.parseInt(cursor.getString(0)));
        command.setCommand(cursor.getString(1));

        return command;
    }

    public Order getSingleOrder(int orderId){

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + OrderContract.OrderEntity.TABLE_NAME + " WHERE "
                + OrderContract.OrderEntity.COLUMN_ORDER_ID + " = " + orderId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Order order = new Order();
        order.setOrder_ID(Integer.parseInt(cursor.getString(0)));
        order.setOrder(cursor.getString(1));
        order.setOrder_Description(cursor.getString(2));

        return order;
    }

    public List<Command> getAllCommands(){
        List<Command> commands = new ArrayList();

        String selectQuery = "SELECT * FROM "+ CommandsContract.CommandsEntity.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Command obj = new Command();
            obj.setCommand_ID(Integer.parseInt(cursor.getString(0)));
            obj.setOrder_ID(Integer.parseInt(cursor.getString(1)));
            obj.setCommand(cursor.getString(2));
            commands.add(obj);
        }
        return commands;
    }

    public List<Order> getAllOrders(int orderId) {
        List<Order> orders = new ArrayList();

        String selectQuery = "SELECT  * FROM " + OrderContract.OrderEntity.TABLE_NAME + " WHERE "
                + OrderContract.OrderEntity.COLUMN_ORDER_ID + " = " + orderId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Order obj = new Order();
            obj.setOrder_ID(Integer.parseInt(cursor.getString(0)));
            obj.setOrder(cursor.getString(1));
            obj.setOrder_Description(cursor.getString(2));
            orders.add(obj);
        }
        return orders;
    }

    public Order getOrderCommand (Command command){
        Order order = new Order();

        String selectQuery = "SELECT  * FROM " + OrderContract.OrderEntity.TABLE_NAME + " WHERE "
                + OrderContract.OrderEntity.COLUMN_ORDER_ID + " = " + command.getOrder_ID();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        order.setOrder_ID(Integer.parseInt(cursor.getString(0)));
        order.setOrder(cursor.getString(1));
        order.setOrder_Description(cursor.getString(2));

        String ord = cursor.getString(2);
        Log.e("errorr",ord);

        return order;
    }
}