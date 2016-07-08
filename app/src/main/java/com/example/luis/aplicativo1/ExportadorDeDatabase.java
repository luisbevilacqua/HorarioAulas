package com.example.luis.aplicativo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by luis on 08/07/16.
 */
public class ExportadorDeDatabase extends SQLiteAssetHelper{

    private static final String DB_NAME = "horarios.db";
    private static final int DB_VERSION = 1;

    public ExportadorDeDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

}
