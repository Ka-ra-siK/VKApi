package ru.eltex.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ru.eltex.R;
import ru.eltex.databinding.ActivityFragmentViewBinding;
import ru.eltex.fragments.FriendsFragment;
import ru.eltex.fragments.GroupsFragment;
import ru.eltex.fragments.MessagesFragment;
import ru.eltex.fragments.NewsFragment;
import ru.eltex.fragments.UserFragment;

public class FragmentViewActivity extends AppCompatActivity {
    ActivityFragmentViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view);
        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        // Get a SharedPreferences.Editor instance
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Get url from previous activity
        String url = (String) getIntent().getSerializableExtra("URL");

        //Parsing URL for 'user_id' and 'access token'
        Map<String, String> map = getQueryMap(url);
        Set<String> keys = map.keySet();

        // Save the strings
        editor.putString("token", map.get("https://oauth.vk.com/blank.html#access_token"));
        editor.putString("user_id", map.get("user_id"));
        editor.putString("expires_in", map.get("expires_in"));

        // Apply the changes
        editor.apply();

        binding = ActivityFragmentViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //replaceFragment(new UserFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.person_menu:
                    replaceFragment(new UserFragment());
                    break;
                case R.id.news_menu:
                    replaceFragment(new NewsFragment(this));
                    break;
                case R.id.group_menu:
                    replaceFragment(new GroupsFragment("groups,publics", "members_count"));
                    break;
                case R.id.friends_menu:
                    replaceFragment(new FriendsFragment(this));
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_view, fragment); // TODO not web_link
        fragmentTransaction.commit();
    }

    public static Map<String, String> getQueryMap(String url) {
        String[] params = url.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }
}