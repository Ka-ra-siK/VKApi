package ru.eltex.api_service_friends;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.activity.FriendsActivity;
import ru.eltex.activity.WebActivity;

public class WebClient extends WebViewClient {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.d("MyWebActivity","onPageStarted()");
        super.onPageStarted(view, url, favicon);

        if (url.contains("access_token")) {
            Log.d("MyWebActivity","access_token");
//            Intent intent = new Intent(WebActivity, FriendsActivity.class);

            System.out.println(url);

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.vk.com/method/")
                    .build();

            VKApiService vkApiService = retrofit.create(VKApiService.class);
            vkApiService.getFriends(181074460).enqueue(new Callback<VKResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<VKResponse> call, Response<VKResponse> response) {
                    assert response.body() != null;
                    response.body().getResponse().getItems().forEach(element -> {
                        Log.d("MyWebActivity_FIRSTNAME",element.getFirstName());
                        Log.d("MyWebActivity_LASTNAME",element.getLastName());
                        System.out.println(element.getFirstName() + " " + element.getLastName());
                    });
                }

                @Override
                public void onFailure(Call<VKResponse> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }
    }
}
