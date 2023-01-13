package ru.eltex.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.eltex.R;
import ru.eltex.api_service_friends.WebClient;
import ru.eltex.databinding.ActivityMainBinding;
import ru.eltex.fragments.FriendsFragment;
import ru.eltex.fragments.MessagesFragment;
import ru.eltex.fragments.NewsFragment;
import ru.eltex.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    //ID of the VK application
    private static String APP_ID = "51509978";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_link);
        //Authorization request
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=" + APP_ID + "&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall&response_type=token&v=5.74&display=mobile");
        webView.setWebViewClient(new WebClient(this));
        replaceFragment(new UserFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.person_menu:
                    replaceFragment(new UserFragment());
                    break;
                case R.id.news_menu:
                    replaceFragment(new NewsFragment());
                    break;
                case R.id.message_menu:
                    replaceFragment(new MessagesFragment());
                    break;
                case R.id.friends_menu:
                    replaceFragment(new FriendsFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.web_link, fragment); // TODO not web_link
        fragmentTransaction.commit();
    }

}