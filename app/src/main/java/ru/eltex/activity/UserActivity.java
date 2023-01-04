package ru.eltex.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.api_service_user.VKApiServiceUser;
import ru.eltex.api_service_user.VKResponseUser;


public class UserActivity extends AppCompatActivity {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firstLastName = findViewById(R.id.first_last_name);
        birthDate = findViewById(R.id.birth_day);
        status = findViewById(R.id.status);
        homeTown = findViewById(R.id.home_town);
        city = findViewById(R.id.city);

        //Get url from previous activity
        String url = (String) getIntent().getSerializableExtra("URL");

        //Parsing URL for 'user_id' and 'access token'
        Map<String, String> map = getQueryMap(url);
        Set<String> keys = map.keySet();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiServiceUser vkApiServiceUser = retrofit.create(VKApiServiceUser.class);

        vkApiServiceUser.getUser(Integer.valueOf(Objects.requireNonNull(map.get("user_id"))),
                map.get("https://oauth.vk.com/blank.html#access_token")).enqueue(new Callback<VKResponseUser>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKResponseUser> call, Response<VKResponseUser> response) {
                assert response.body() != null;
                firstLastName.setText(response.body().getResponse().getFirstName() + " " + response.body().getResponse().getLastName());
                status.setText(response.body().getResponse().getStatus());
                birthDate.setText(response.body().getResponse().getBirthDate());
                homeTown.setText(response.body().getResponse().getHomeTown());
                city.setText(response.body().getResponse().getCity());
            }

            @Override
            public void onFailure(Call<VKResponseUser> call, Throwable t) {

            }

        });
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
