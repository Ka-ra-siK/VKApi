package ru.eltex.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.FriendsAdapter;
import ru.eltex.api_service_friends.VKApiService;
import ru.eltex.api_service_friends.VKResponse;
import ru.eltex.instance.Friend;

public class FriendsActivity extends AppCompatActivity {

    static List<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        //Get url from previous activity
        String url = (String) getIntent().getSerializableExtra("URL");

        //Creating List of friends
        ListView friendsList = (ListView) findViewById(R.id.friends_list);
        friends = new LinkedList<>();

        //Parsing URL for 'user_id' and 'access token'
        Map<String, String> map = getQueryMap(url);
        Set<String> keys = map.keySet();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiService = retrofit.create(VKApiService.class);

        //Creating a getFriends request
        vkApiService.getFriends(Integer.valueOf(Objects.requireNonNull(map.get("user_id"))), map.get("https://oauth.vk.com/blank.html#access_token")).enqueue(new Callback<VKResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKResponse> call, Response<VKResponse> response) {
                assert response.body() != null;
                response.body().getResponse().getItems().forEach(element -> {
                    friends.add(new Friend(element.getFirstName(), element.getLastName(), element.getSex()));

                });
                TextView friendsCount = (TextView) findViewById(R.id.friends_count);
                friendsCount.setText(response.body().getResponse().getCount().toString());
                FriendsAdapter friendsAdapter = new FriendsAdapter(FriendsActivity.this, friends);
                friendsList.setAdapter(friendsAdapter);
            }

            @Override
            public void onFailure(Call<VKResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //Parsing URL
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