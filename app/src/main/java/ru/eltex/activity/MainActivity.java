package ru.eltex.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ru.eltex.R;
import ru.eltex.databinding.ActivityMainBinding;
import ru.eltex.fragments.FriendsFragment;
import ru.eltex.fragments.GroupsFragment;
import ru.eltex.fragments.NewsFragment;
import ru.eltex.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    //ID of the VK application
    private static String APP_ID = "51509978";
    private static final int WEBVIEW_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start the second activity
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivityForResult(intent, WEBVIEW_REQUEST, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WEBVIEW_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get the data passed back from the second activity
                // Get a SharedPreferences instance
                SharedPreferences sharedPreferences = getSharedPreferences("access_preference", Context.MODE_PRIVATE);

                // Get a SharedPreferences.Editor instance
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //Get url from previous activity
                String url = data.getStringExtra("URL");

                Log.d("RESULT_URL", url);

                //Parsing URL for 'user_id' and 'access token'
                Map<String, String> map = getQueryMap(url);
                Set<String> keys = map.keySet();

                // Save the strings
                editor.putString("token", map.get("https://oauth.vk.com/blank.html#access_token"));
                editor.putString("user_id", map.get("user_id"));
                editor.putString("expires_in", map.get("expires_in"));

                // Apply the changes
                editor.apply();

                binding = ActivityMainBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());
                replaceFragment(new UserFragment());
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
        }
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