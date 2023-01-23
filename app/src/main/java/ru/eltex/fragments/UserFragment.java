package ru.eltex.fragments;

import android.annotation.SuppressLint;
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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.adapters.SliderAdapter;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.user.VKUserResponse;
import ru.eltex.api_service.user.photo.VKUserPhotoResponse;
import ru.eltex.api_service.user.photo.VKUserPhotoResponseBody;

public class UserFragment extends Fragment {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView city;
    private ImageView userImg;
    private ImageView groupsImg;
    private ArrayList<String> photoURLs;
    private ArrayList<String> photoLikes;
    private ArrayList<String> photoReposts;
    private SliderAdapter sliderAdapter;
    // creating object of ViewPager
    ViewPager sliderViewPager;


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
        groupsImg = (ImageView) view.findViewById(R.id.groups_img);
        sliderViewPager = (ViewPager) view.findViewById(R.id.photo_view_pager);

        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

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
                Log.d("RETURN_BODY", "onResponse()");
                assert response.body() != null;
                response.body().getResponse().forEach(element -> {
                    Log.d("RETURN_BODY", element.getFirstName());
                    firstLastName.setText(element.getFirstName() + " " + element.getLastName());
                    status.setText(element.getStatus());
                    birthDate.setText(element.getBirthDate());
                    homeTown.setText(element.getHomeTown());
                    new TaskRunner().executeAsync(new ImageLoadTask(element.getPhoto100()), (image) -> {
                        userImg.setImageBitmap(image);
                        Bitmap bitmap = ((BitmapDrawable) userImg.getDrawable()).getBitmap();
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                        roundedBitmapDrawable.setCircular(true);
                        userImg.setImageDrawable(roundedBitmapDrawable);
                    });
                    //new ImageLoadTask(element.getPhoto100(), userImg).execute();

                });
            }

            @Override
            public void onFailure(Call<VKUserResponse> call, Throwable t) {
                Log.d("RETURN_BODY", "onFailure()");
                Log.e("RETURN_BODY", String.valueOf(t));

            }

        });


        //Просмотр фотографий пользователя
        VKApiService vkApiServiceUserPhoto = retrofit.create(VKApiService.class);
        vkApiServiceUserPhoto.getUserPhoto(userId, token,
                "profile", "1", 1,Double.valueOf("5.131")).enqueue(new Callback<VKUserPhotoResponse>() {
            @Override
            public void onResponse(Call<VKUserPhotoResponse> call, Response<VKUserPhotoResponse> response) {
                photoURLs = new ArrayList<>();
                photoLikes = new ArrayList<>();
                photoReposts = new ArrayList<>();
                assert response.body() != null;
                Log.d("PHOTO", String.valueOf(response.body().getResponse().getCount()));
                response.body().getResponse().getPhotoResponseItems().forEach(elements -> {
                    photoURLs.add(elements.getPhotoResponseItemsSizesList().get(elements.getSizes() - 1).getUrl());
                    photoLikes.add(String.valueOf(elements.getLikes().getCount()));
                    photoReposts.add(String.valueOf(elements.getReposts().getCount()));
                });
                sliderAdapter = new SliderAdapter(getContext(), photoURLs);
                sliderAdapter.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        Bundle bundle = new Bundle();
                        bundle.putString("photo_id", photoURLs.get(position));
                        bundle.putString("likes", photoLikes.get(position));
                        bundle.putString("reposts", photoReposts.get(position));
                        PhotoViewFragment photoViewFragment = new PhotoViewFragment();
                        photoViewFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, photoViewFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });
                sliderViewPager.setAdapter(sliderAdapter);
            }

            @Override
            public void onFailure(Call<VKUserPhotoResponse> call, Throwable t) {
                Log.e("PHOTO", String.valueOf(t));

            }
        });

        groupsImg.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("user_id", userId);
            GroupsFragment groupsFragment = new GroupsFragment("groups,publics", "members_count");
            groupsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, groupsFragment).commit();
        });
        return view;
    }
}