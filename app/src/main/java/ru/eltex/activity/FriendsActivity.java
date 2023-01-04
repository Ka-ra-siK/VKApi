package ru.eltex.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.FriendsAdapter;
import ru.eltex.api_service_friends.VKApiService;
import ru.eltex.api_service_friends.VKResponse;
import ru.eltex.friends.Friend;

public class FriendsActivity extends AppCompatActivity {

    static List<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ListView friendsList = (ListView) findViewById(R.id.friends_list);

        friends = new LinkedList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiService = retrofit.create(VKApiService.class);
        vkApiService.getFriends(181074460).enqueue(new Callback<VKResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKResponse> call, Response<VKResponse> response) {
                assert response.body() != null;
                response.body().getResponse().getItems().forEach(element -> {
                    Log.d("MyWebActivity_FIRSTNAME", element.getFirstName());
                    Log.d("MyWebActivity_LASTNAME", element.getLastName());
                    friends.add(new Friend(element.getFirstName(), element.getLastName(), element.getSex()));
                    System.out.println(element.getFirstName() + " " + element.getLastName());
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
}