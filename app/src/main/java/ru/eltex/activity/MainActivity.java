package ru.eltex.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import ru.eltex.R;
import ru.eltex.api_service.WebClient;

public class MainActivity extends AppCompatActivity {
    //ID of the VK application
    private static String APP_ID = "51509978";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.web_link);
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=" + APP_ID + "&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall&response_type=token&v=5.74&display=mobile");
        webView.setWebViewClient(new WebClient(this));

    }

}