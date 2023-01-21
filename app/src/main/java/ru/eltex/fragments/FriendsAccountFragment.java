package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.user.VKUserResponse;

public class FriendsAccountFragment extends Fragment {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView city;
    private ImageView userImg;
    private ImageView groupsImg;
    private ImageView isOnlineImage;

    private String friendId;
    private String token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends_account, container, false);

        Bundle bundle = getArguments();
        friendId = bundle.getString("friend_id");

        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        token = sharedPreferences.getString("token", "myToken");

        firstLastName = (TextView) view.findViewById(R.id.first_last_name_friend);
        birthDate = (TextView) view.findViewById(R.id.birth_day_friend);
        status = (TextView) view.findViewById(R.id.status_friend);
        homeTown = (TextView) view.findViewById(R.id.home_town_friend);
//        city = (TextView) view.findViewById(R.id.city_friend);
        userImg = (ImageView) view.findViewById(R.id.user_friend_img);
        groupsImg = (ImageView) view.findViewById(R.id.groups_img);
        isOnlineImage = (ImageView) view.findViewById(R.id.is_online_friend);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceUserFriend = retrofit.create(VKApiService.class);

        vkApiServiceUserFriend.getUser(friendId, token).enqueue(new Callback<VKUserResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKUserResponse> call, Response<VKUserResponse> response) {
                assert response.body() != null;
                response.body().getResponse().forEach(element -> {
                    Log.d("GET_FRIEND", friendId);
                    firstLastName.setText(element.getFirstName() + " " + element.getLastName());
                    status.setText(element.getStatus());
                    birthDate.setText(element.getBirthDate());
                    homeTown.setText(element.getHomeTown());
//                    city.setText(element.getVkUserCity().getTitle());
                    new TaskRunner().executeAsync(new ImageLoadTask(element.getPhoto100()), (image) -> {
                        userImg.setImageBitmap(image);
                        Bitmap bitmap = ((BitmapDrawable) userImg.getDrawable()).getBitmap();
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                        roundedBitmapDrawable.setCircular(true);
                        userImg.setImageDrawable(roundedBitmapDrawable);
                    });

                    if (Objects.equals(element.getOnline(), "1")){
                        if (element.getOnlineMobile() != null){
                            isOnlineImage.setImageResource(R.drawable.phone);
                        }else {
                            isOnlineImage.setImageResource(R.drawable.is_online);
                        }
                    }
                    //new ImageLoadTask(element.getPhoto100(), userImg).execute();

                });
            }

            @Override
            public void onFailure(Call<VKUserResponse> call, Throwable t) {
                Log.e("GET_FRIEND", "Ошибка загрузки!");
                Log.e("GET_FRIEND", String.valueOf(t));

            }
        });
        groupsImg.setOnClickListener(view1 -> {
            Bundle bundleGroups = new Bundle();
            bundleGroups.putString("user_id", friendId);
            GroupsFragment groupsFragment = new GroupsFragment("publics", "members_count");
            groupsFragment.setArguments(bundleGroups);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, groupsFragment).commit();
        });


        return view;
    }
}