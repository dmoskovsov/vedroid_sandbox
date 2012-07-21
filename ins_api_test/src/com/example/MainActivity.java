package com.example;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity
{

    private Button gotoCameraViewButton;

    private static final int GET_PHOTO_REQUEST = 32323;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        loadImage("http://distilleryimage10.instagram.com/09ff90becb8f11e1a94522000a1e8aaf_7.jpg", R.id.image1);

        gotoCameraViewButton = (Button) findViewById(R.id.gotoCameraViewButton);
        gotoCameraViewButton.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(android.view.View v)
            {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
//        intent.putExtra("ComingFrom", "Acticity 1");

          MainActivity.this.startActivityForResult(intent, GET_PHOTO_REQUEST);
            }
        });

        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
//            case (SUB_ACTIVITY_ID): {
//                break;
//            }
        }
    }


}
