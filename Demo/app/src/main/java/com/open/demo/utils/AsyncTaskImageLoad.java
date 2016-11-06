package com.open.demo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mac on 2016/10/30.
 */

public class AsyncTaskImageLoad extends AsyncTask<String, Integer, Bitmap> {

    private ImageView Image=null;

    public AsyncTaskImageLoad(ImageView img)
    {
        Image=img;
    }
    //运行在子线程中
    @Override
    protected Bitmap doInBackground(String... params) {
        try
        {
            URL url = new URL(params[0]);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            System.out.println();
            if(conn.getResponseCode()==200)
            {
                InputStream input=conn.getInputStream();
                Bitmap map= BitmapFactory.decodeStream(input);
                return map;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result)
    {
        if(Image!=null && result!=null) {
            Image.setImageBitmap(result);
        }

        super.onPostExecute(result);
    }
}