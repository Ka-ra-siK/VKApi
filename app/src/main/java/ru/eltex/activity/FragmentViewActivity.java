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
import ru.eltex.fragments.FriendsFragment;
import ru.eltex.fragments.UserFragment;

public class FragmentViewActivity extends AppCompatActivity {

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

        // Apply the changes
        editor.apply();

    }

    public void chooseFragment(View view){
        Fragment fragment = null;

        switch (view.getId()){
            case R.id.to_friends_button:
                fragment = new FriendsFragment(this);
                break;
            case R.id.to_user_button:
                fragment = new UserFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        assert fragment != null;
        fragmentTransaction.replace(R.id.fragment_view, fragment);
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