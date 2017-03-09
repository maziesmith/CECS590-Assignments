package android.csulb.edu.notedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sumeet on 3/7/2017.
 */

public class DBManager{
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertData(String imagePath, String captionText){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NOTE_PATH,imagePath);
        contentValues.put(DBHelper.NOTE_CAPTION,captionText);
        long result=database.insert(DBHelper.TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public Cursor fetch(){
        String[] columns = new String[] {DBHelper._ID, DBHelper.NOTE_CAPTION};
        Cursor cursor = database.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchEntry(int id){
        String[] projection= {
                DBHelper._ID,
                DBHelper.NOTE_CAPTION,
                DBHelper.NOTE_PATH
        };
        String selection= DBHelper._ID+ " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = database.query(
                DBHelper.TABLE_NAME,                    // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                // The sort order
        );
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }


}
