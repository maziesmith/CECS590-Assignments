package android.csulb.edu.notedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sumeet on 2/26/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Note.db";
    public static final String TABLE_NAME = "notedetails";
    public static final String _ID = "_id";
    public static final String NOTE_PATH = "filepath";
    public static final String NOTE_CAPTION = "caption";

    public DBHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(
                "create table notedetails " +
                        "(_id integer primary key AutoIncrement,filepath text,caption text)"
        );

        Log.d("oncreate", "onCreate success ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
