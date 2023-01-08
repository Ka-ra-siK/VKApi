package ru.eltex.api_service_friends;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.eltex.activity.FriendsActivity;
import ru.eltex.activity.NewsActivity;
import ru.eltex.activity.UserActivity;

public class WebClient extends WebViewClient {

    //Saving context
    private Context context;

    public WebClient(Context context) {
        this.context = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        //When authorization success redirect to base activity
        if (url.contains("blank.html#access_token")) {
            Intent intent = new Intent(context, UserActivity.class);
            intent.putExtra("URL", url);
            context.startActivity(intent);
        }
    }
}
