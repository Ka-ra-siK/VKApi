package ru.eltex.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_user.VKUserResponse;
import ru.eltex.instance.Friend;

public class UserFragment extends Fragment {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView city;
    private ImageView userImg;

    private String token;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        firstLastName = (TextView) view.findViewById(R.id.first_last_name);
        birthDate = (TextView) view.findViewById(R.id.birth_day);
        status = (TextView) view.findViewById(R.id.status);
        homeTown = (TextView) view.findViewById(R.id.home_town);
//        city = (TextView) view.findViewById(R.id.city);
        userImg = (ImageView) view.findViewById(R.id.user_img);


        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

        Log.d("SHARED_DATA", sharedPreferences.getString("token", token));
        Log.d("SHARED_DATA", sharedPreferences.getString("user_id", userId));

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceUser = retrofit.create(VKApiService.class);

        vkApiServiceUser.getUser(userId, token).enqueue(new Callback<VKUserResponse>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKUserResponse> call, Response<VKUserResponse> response) {
                Log.d("RETURN_BODY","onResponse()");
                assert response.body() != null;
                response.body().getResponse().forEach(element -> {
                    Log.d("RETURN_BODY", element.getFirstName());
                    firstLastName.setText(element.getFirstName() + " " + element.getLastName());
                    status.setText(element.getStatus());
                    birthDate.setText(element.getBirthDate());
                    homeTown.setText(element.getHomeTown());
                    new ImageLoadTask(element.getPhoto100(), userImg).execute();

                });
            }

            @Override
            public void onFailure(Call<VKUserResponse> call, Throwable t) {
                Log.d("RETURN_BODY","onFailure()");
                Log.e("RETURN_BODY", String.valueOf(t));

            }

        });
        return view;
    }
}