package com.example.cameraexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri picUri;
    final int PIC_CROP = 2;
    ImageView imageViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }
}

    private void findViews() {
     imageViewPhoto  = findViewById(R.id.img_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

  if(resultCode == RESULT_OK){
      if( requestCode == REQUEST_IMAGE_CAPTURE){
          picUri = data.getData();
          performCrop();
      }
      else if(requestCode == PIC_CROP){

          Bundle extras = data.getExtras();
          Bitmap thePic = extras.getParcelable("data");
          imageViewPhoto.setImageBitmap(thePic);
      }
  }

    }

    private void performCrop() {

        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri , "image/*");
            cropIntent.putExtra("crop" , "true");
            cropIntent.putExtra("aspectX" , 1);
            cropIntent.putExtra("aspectY" , 1);
            cropIntent.putExtra("outputX" , 256);
            cropIntent.putExtra("outputY" , 256);
            cropIntent.putExtra("return-data" , true);
            startActivityForResult(cropIntent , PIC_CROP);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }





    }
}