package com.example.apple.miniproject;


import android.content.ContentValues;
import android.content.Context;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 15/07/17.
 */

public class Contract {

    Context context;
    private final Helper helper;

    public Contract(Context context) {

        this.context = context;
        helper = new Helper(context);
    }
    public long add(String name) //insert query
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //it basically tells which column will have which value and all the columns are kept in bundle here values is content
        values.put(Constants.COL_RESULT,name);

        return db.insert(Constants.TBL_MOVIES, null, values);
        //first parameter tells table name then null column hack its always null then values
    }

    public Cursor list()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        return db.query(Constants.TBL_MOVIES, null, null, null, null, null, null);

    }

    public void delete() {
        SQLiteDatabase db = helper.getWritableDatabase();

         db.execSQL("delete from "+ Constants.TBL_MOVIES);
    }

    public int updateById(String whichId, String name, int year,double rating,String studio){
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = Constants.COL_ID + "=?";
        String[] whereArgs = new String[]{whichId};

        ContentValues values = new ContentValues();
        values.put(Constants.COL_RESULT, name);//instead of writing constants we could have written the original column name but it could have been confusing


        return db.update(Constants.TBL_MOVIES,values,where,whereArgs);
    }


    private class Helper extends SQLiteOpenHelper {

        public Helper(Context context) {
            super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String creationQueryModel = "CREATE TABLE history (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "result VARCHAR(255)" + ")";
            db.execSQL(creationQueryModel);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropQueryMovies = "DROP TABLE IF EXISTS history";
            db.execSQL(dropQueryMovies);
            onCreate(db);
        }
    }
    public interface Constants {

        public static final String TBL_MOVIES = "history";
        public static final String COL_ID = "_id";
        public static final String COL_RESULT = "result";
        public static final String DB_NAME = "mydb";
        public static final int DB_VERSION = 1;


    }
}
