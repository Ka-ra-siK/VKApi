package ru.eltex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Класс выполняющий асинхронную загрузку изображений
 */
public class ImageLoadTask implements Callable<Bitmap> {

    private final String url;

    public ImageLoadTask(String url) {
        this.url = url;
    }

    @Override
    public Bitmap call() throws Exception {
        URL urlConnection = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlConnection
                .openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }

//    @Override
//    protected Bitmap doInBackground(Void... params) {
//        try {
//            URL urlConnection = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) urlConnection
//                    .openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            return BitmapFactory.decodeStream(input);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap result) {
//        super.onPostExecute(result);
//        imageView.setImageBitmap(result);
//    }

}
