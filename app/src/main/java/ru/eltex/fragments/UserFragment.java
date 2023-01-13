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
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_user.VKResponseUser;

public class UserFragment extends Fragment {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView city;

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
        city = (TextView) view.findViewById(R.id.city);


        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

        Log.d("SHARED_DATA", sharedPreferences.getString("token", token));
        Log.d("SHARED_DATA", sharedPreferences.getString("user_id", userId));

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceUser = retrofit.create(VKApiService.class);

        vkApiServiceUser.getUser(Integer.valueOf(Objects.requireNonNull(userId)), token).enqueue(new Callback<VKResponseUser>() {
            @SuppressLint("SetTextI18n")
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
        return view;
    }
}