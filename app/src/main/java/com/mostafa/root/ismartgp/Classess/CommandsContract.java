package com.mostafa.root.ismartgp.Classess;

import android.provider.BaseColumns;

/**
 * Created by AhmedEltoukhy on 30-Oct-17.
 */

public class CommandsContract {

    public static class CommandsEntity implements BaseColumns {

        public static final String TABLE_NAME = "Commands";
        public static final String COLUMN_COMMAND_ID = "command_id";
        public static final String COLUMN_COMMAND = "command";
        public static final String COLUMN_ORDER_ID = "order_id";

        static final String SQL_QUERY_CREATE =
                "CREATE TABLE " + CommandsEntity.TABLE_NAME + " ("
                        + CommandsEntity.COLUMN_COMMAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + CommandsEntity.COLUMN_ORDER_ID + " INTEGER NOT NULL,"
                        + CommandsEntity.COLUMN_COMMAND + " TEXT NOT NULL UNIQUE"
                        + ")";
    }
}