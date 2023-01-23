package ru.eltex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import ru.eltex.R;

public class RegistrationActivity extends AppCompatActivity {
    //ID of the VK application
    private static String APP_ID = "51509978";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        WebView webView = findViewById(R.id.web_link);
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=" + APP_ID + "&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,groups,video&response_type=token&v=5.74&display=mobile");
        webView.setWebViewClient(new RegistrationWebViewClient(this));
    }

    public class RegistrationWebViewClient extends WebViewClient{
        //Saving context
        private Activity activity;

        public RegistrationWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (url.contains("blank.html#access_token")) {

                //When authorization success redirect to base activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("URL", url);
                activity.setResult(Activity.RESULT_OK, resultIntent);
                activity.finish();
            }
        }
    }
}