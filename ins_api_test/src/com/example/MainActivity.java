package com.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity
{

    private final int CAMERA_VIEW_REUEST_CODE = 1;

    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        loadImage("http://distilleryimage10.instagram.com/09ff90becb8f11e1a94522000a1e8aaf_7.jpg", R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);
        imageView4 = (ImageView) findViewById(R.id.image4);
        gotoCamera(null);
    }


    public void gotoCamera(View view)
    {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivityForResult(intent, CAMERA_VIEW_REUEST_CODE);
    }

    public void loadImage(String imageUrl, int image1)
    {
        try
        {
            ImageView imageView1 = (ImageView) findViewById(image1);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            imageView1.setImageBitmap(bitmap);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        try
        {
            if (requestCode == CAMERA_VIEW_REUEST_CODE)
            {//indicate that wiew is  camera view ( don't use for now
                if (resultCode == RESULT_OK)
                {//get the picture

                    Bundle extras = intent.getExtras();
                    String fileName = (String) extras.get(Constants.PICTURE_FILE_PATH_KEY);
//                    Toast.makeText(this, "get picture", Toast.LENGTH_SHORT).show();
                    drawPicture(fileName);
                }
            }
        } catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    private void drawPicture(String fileName)
    {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        try
        {
            fileInputStream = new FileInputStream(fileName);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            imageView3.setImageBitmap(bitmap);

            if (fileInputStream != null)
            {
                fileInputStream.close();
            }
            if (bufferedInputStream != null)
            {
                bufferedInputStream.close();
            }

        } catch (Exception e)
        {
            Log.e("Error reading file", e.toString());
        }
    }

}
