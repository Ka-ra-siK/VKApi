package ru.eltex.api_service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.eltex.activity.FragmentViewActivity;

public class WebClient extends WebViewClient {

    //Saving context
    private Context context;

    public WebClient(Context context) {
        this.context = context;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (url.contains("blank.html#access_token")) {

            //When authorization success redirect to base activity
            Intent intent = new Intent(context, FragmentViewActivity.class);
            intent.putExtra("URL", url);
            context.startActivity(intent);
        }
    }
}
