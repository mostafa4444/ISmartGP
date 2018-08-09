package com.mostafa.root.ismartgp.Classess;

import android.provider.BaseColumns;

/**
 * Created by AhmedEltoukhy on 30-Oct-17.
 */

public class OrderContract {

    public static class OrderEntity implements BaseColumns {

        public static final String TABLE_NAME = "Orders";
        public static final String COLUMN_ORDER_ID = "order_id";
        public static final String COLUMN_ORDER = "order_title";
        public static final String COLUMN_DESCRIPTION = "order_description";

        static final String SQL_QUERY_CREATE =
                "CREATE TABLE " + OrderEntity.TABLE_NAME + "("
                        + OrderEntity.COLUMN_ORDER_ID + " INTEGER PRIMARY KEY,"
                        + OrderEntity.COLUMN_ORDER + " TEXT NOT NULL,"
                        + OrderEntity.COLUMN_DESCRIPTION + " TEXT NOT NULL"
                        + ")";
    }
}
