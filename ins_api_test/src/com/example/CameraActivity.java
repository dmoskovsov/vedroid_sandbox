package com.example;


import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Daniil Moskovsov <qa000100@gmail.com>
 * @version $Id$
 */
public class CameraActivity extends Activity implements SurfaceHolder.Callback
{
    private boolean firstTime;
    private static final String TAG = "CameraDemo";

    Preview preview;


    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;;

    String stringPath = "/sdcard/samplevideo.3gp";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        Button buttonStartCameraPreview = (Button)findViewById(R.id.startcamerapreview);
        Button buttonStopCameraPreview = (Button)findViewById(R.id.stopcamerapreview);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        buttonStartCameraPreview.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!previewing){
                    camera = Camera.open();
                    if (camera != null){
                        try {
                            camera.setDisplayOrientation(90);
                            camera.setPreviewDisplay(surfaceHolder);
                            camera.startPreview();
                            previewing = true;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }});

        buttonStopCameraPreview.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(camera != null && previewing){
                    camera.stopPreview();
                    camera.release();
                    camera = null;

                    previewing = false;
                }
            }});

    }



    /**
     * Called when the activity is first created.
     * //
     */
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.camera);
//
//        preview = new Preview(this);
//        final FrameLayout previewView = (FrameLayout) findViewById(R.id.preview);
//        previewView.addView(preview);
//    }


    public void gotoBackToMainView(View view)
    {
        //first we will take a picture then close the view
        preview.camera.takePicture(null, null, jpegCallback);
    }

    /**
     * Handles data for jpeg picture
     */
    PictureCallback jpegCallback = new PictureCallback()
    {
        public void onPictureTaken(byte[] data, Camera camera)
        {
            String fileName = getFilePath();
            writeFileToFileSystem(data, fileName);
            sentFileNameToTheMainActivity(fileName);
        }
    };


    private String getFilePath()
    {
        String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String fileName = "/sdcard/" + timeStamp + ".jpg";
        return fileName;  //To change body of created methods use File | Settings | File Templates.
    }


    public void writeFileToFileSystem(byte[] data, String filePath)
    {
        FileOutputStream outStream = null;
        try
        {
            outStream = new FileOutputStream(filePath);
            outStream.write(data);
            outStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
        }
    }


    private void sentFileNameToTheMainActivity(String filePath)
    {
        try
        {
            Intent intent = new Intent();
            //Set the data to pass back
            intent.putExtra(Constants.PICTURE_FILE_PATH_KEY, filePath);
            setResult(RESULT_OK, intent);
            //Close the activity
            finish();

        } catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }




    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }
}