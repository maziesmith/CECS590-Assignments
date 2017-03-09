package android.csulb.edu.notedatabase;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBManager dbManager = new DBManager(this);
        setContentView(R.layout.activity_view_photo);

        TextView textView =(TextView) findViewById(R.id.textView);
        Bundle myBundle = this.getIntent().getExtras();
        int photoId = myBundle.getInt("photoId");

        dbManager.open();
        Cursor cursor = dbManager.fetchEntry(photoId);
        String filePath = cursor.getString(cursor.getColumnIndex("filepath"));
        String caption = cursor.getString(cursor.getColumnIndex("caption"));
        dbManager.close();

        textView.setText("Caption :"+caption);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);


    }
}
