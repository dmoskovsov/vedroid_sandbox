package com.example;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/**
 * @author Daniil Moskovsov <qa000100@gmail.com>
 * @version $Id$
 */
public class CameraActivity extends Activity
{
    private boolean firstTime;
    private static final String TAG = "CameraDemo";
    Camera camera;
    Preview preview;
    Button buttonClick;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

            preview = new Preview(this);
            final android.widget.FrameLayout previewView = (android.widget.FrameLayout) findViewById(R.id.preview);

        if (firstTime == false)
        {
            firstTime = true;
            previewView.addView(preview);
        }
        buttonClick = (Button) findViewById(R.id.buttonClick);
        buttonClick.setOnClickListener(new android.view.View.OnClickListener()
        {
            public void onClick(android.view.View v)
            {

                Log.i(TAG, "Back Button Passed");
                setResult(RESULT_CANCELED, null);
                finish();

////           preview.camera.takePicture(shutterCallback, rawCallback, jpegCallback);
//                Intent intent = new Intent();
////                intent.putExtra("ComingFrom", data);
//                setResult(RESULT_OK, intent);
//                previewView.removeView(preview);
//
//                finish();
            }
        });
    }

    /**
     * Handles data for jpeg picture
     */
    PictureCallback jpegCallback = new PictureCallback()
    {

        public void onPictureTaken(byte[] data, Camera camera)
        {
            Intent intent = new Intent();
            intent.putExtra("ComingFrom", data);
            setResult(RESULT_OK, intent);
            finish();
        }

//        public void onPictureTaken(byte[] data, Camera camera)
//        {
//            FileOutputStream outStream = null;
//            try
//            {
        // write to local sandbox file system
        // outStream =
        // CameraDemo.this.openFileOutput(String.format("%d.jpg",
        // System.currentTimeMillis()), 0);
        // Or write to sdcard
//                outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
//                outStream.write(data);
//                outStream.close();
//
//                Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
//
//
//            } catch (FileNotFoundException e)
//            {
//                e.printStackTrace();
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            } finally
//            {
//            }
//            Log.d(TAG, "onPictureTaken - jpeg");
//        }
    };

    ShutterCallback shutterCallback = new ShutterCallback()
    {
        public void onShutter()
        {
            Log.d(TAG, "onShutter'd");
        }
    };

    /**
     * Handles data for raw picture
     */
    PictureCallback rawCallback = new PictureCallback()
    {
        public void onPictureTaken(byte[] data, Camera camera)
        {
            Log.d(TAG, "onPictureTaken - raw");
        }
    };


}