package ru.eltex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import ru.eltex.R;
import ru.eltex.api_service_friends.WebClient;

public class WebActivity extends AppCompatActivity {

    private static String USER_ID;
    private static String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.web_link);
        USER_ID = "51509978";
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=" + USER_ID + "&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.74");
        webView.setWebViewClient(new WebClient());
    }
}