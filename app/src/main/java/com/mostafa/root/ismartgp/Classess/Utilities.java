package com.mostafa.root.ismartgp.Classess;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

public  class Utilities{

    public void alertDialog(Context mContext, String header, String content, String pos, DialogInterface.OnClickListener posLis, String neg, DialogInterface.OnClickListener negLis) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mContext,android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(header)
                .setMessage(content)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(pos, posLis)
                .setNegativeButton(neg, negLis)
                .show();
    }
}
