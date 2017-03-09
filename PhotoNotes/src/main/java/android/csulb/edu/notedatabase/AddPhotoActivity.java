package android.csulb.edu.notedatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPhotoActivity extends AppCompatActivity  {

    DBManager dbManager;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editCaption;
    private Bitmap imageBitmap;
    private String myCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        dbManager = new DBManager(this);
    }

    public void onClickCamera(View view) {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try{
            imageFile = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error in file creation",Toast.LENGTH_SHORT).show();
        }

        if(imageFile!=null){
            myCurrentPhotoPath = imageFile.getAbsolutePath();
            System.out.println("New photo path"+myCurrentPhotoPath );
        }

        //Save a file: path for use with ACTION_VIEW intents
        return imageFile;
    }

    public void onClickCancel(View view){
        finish();
    }

    public void onClickSave(View view) {
        Bitmap saveBitMap = imageBitmap;

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        saveBitMap.compress(Bitmap.CompressFormat.JPEG,45,byteArray);
        byte[] compressedData = byteArray.toByteArray();

        FileOutputStream fos;
        try{
            fos = new FileOutputStream(createImageFile());
            fos.write(compressedData);
            fos.flush();
            fos.close();
        }
        catch(IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error while saving file",Toast.LENGTH_SHORT).show();
        }

        editCaption = (EditText) findViewById(R.id.captionText);
        String caption = editCaption.getText().toString();
        boolean isInserted=false;

        if (!(caption.equals(""))) {
            dbManager.open();
            isInserted = dbManager.insertData(myCurrentPhotoPath, caption);
            dbManager.close();
        }

        if(isInserted==true) {
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(this,"Please enter caption",Toast.LENGTH_SHORT).show();
    }
}
