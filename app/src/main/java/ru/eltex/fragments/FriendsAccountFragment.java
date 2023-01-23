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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Objects;

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

public class FriendsAccountFragment extends Fragment {

    private TextView firstLastName;
    private TextView birthDate;
    private TextView status;
    private TextView homeTown;
    private TextView isPrivate;
    private ImageView userImg;
    private ImageView groupsImg;
    private ImageView friendsImg;
    private ImageView isOnlineImage;

    private ArrayList<String> photoURLs;
    private ArrayList<String> photoLikes;
    private ArrayList<String> photoReposts;
    private SliderAdapter sliderAdapter;
    // creating object of ViewPager
    ViewPager sliderViewPager;

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
        isPrivate = (TextView) view.findViewById(R.id.if_account_is_closed);
        userImg = (ImageView) view.findViewById(R.id.user_friend_img);
        groupsImg = (ImageView) view.findViewById(R.id.groups_img);
        friendsImg = (ImageView) view.findViewById(R.id.friends_img);
        isOnlineImage = (ImageView) view.findViewById(R.id.is_online_friend);
        sliderViewPager = (ViewPager) view.findViewById(R.id.photo_view_pager);

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

                    if (Objects.equals(element.getOnline(), "1")) {
                        if (element.getOnlineMobile() != null) {
                            isOnlineImage.setImageResource(R.drawable.phone);
                        } else {
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

        //Просмотр фотографий пользователя
        VKApiService vkApiServiceUserPhoto = retrofit.create(VKApiService.class);
        vkApiServiceUserPhoto.getUserPhoto(friendId, token,
                "profile", "1", 1, Double.valueOf("5.131")).enqueue(new Callback<VKUserPhotoResponse>() {
            @Override
            public void onResponse(Call<VKUserPhotoResponse> call, Response<VKUserPhotoResponse> response) {
                photoURLs = new ArrayList<>();
                photoLikes = new ArrayList<>();
                photoReposts = new ArrayList<>();
                assert response.body() != null;
//                Log.d("PHOTO", String.valueOf(response.body().getResponse().getCount()));
                try {
                    response.body().getResponse().getPhotoResponseItems().forEach(elements -> {
                        photoURLs.add(elements.getPhotoResponseItemsSizesList().get(elements.getSizes() - 1).getUrl());
                        try {
                            photoLikes.add(String.valueOf(elements.getLikes().getCount()));
                            photoReposts.add(String.valueOf(elements.getReposts().getCount()));
                        } catch (Exception e) {

                        }

                    });
                } catch (NullPointerException e){
                    Log.e("NullPointerException", String.valueOf(e));
                }

                sliderAdapter = new SliderAdapter(getContext(), photoURLs);
                sliderAdapter.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        Bundle bundle = new Bundle();
                        bundle.putString("photo_id", photoURLs.get(position));
                        try {
                            bundle.putString("likes", photoLikes.get(position));
                            bundle.putString("reposts", photoReposts.get(position));
                        } catch (Exception e) {

                        }

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
            Bundle bundleGroups = new Bundle();
            bundleGroups.putString("user_id", friendId);
            GroupsFragment groupsFragment = new GroupsFragment("publics", "members_count");
            groupsFragment.setArguments(bundleGroups);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_view, groupsFragment)
                    .addToBackStack(null)
                    .commit();
        });

        friendsImg.setOnClickListener(view2 -> {
            Bundle bundleGroups = new Bundle();
            bundleGroups.putString("user_id", friendId);
            FriendsFragment friendsFragment = new FriendsFragment(this.getContext());
            friendsFragment.setArguments(bundleGroups);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_view, friendsFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}