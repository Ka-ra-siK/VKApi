package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.FriendsAdapter;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_friends.VKFriendsResponse;
import ru.eltex.instance.Friend;

public class FriendsFragment extends Fragment {

    static List<Friend> friends;
    private final Context context;
    private String token;
    private String userId;

    public FriendsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

        //Creating List of friends
        ListView friendsList = (ListView) view.findViewById(R.id.friends_list);
        friends = new LinkedList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiService = retrofit.create(VKApiService.class);

        //Creating a getFriends request
        vkApiService.getFriends(Integer.valueOf(Objects.requireNonNull(userId)), token).enqueue(new Callback<VKFriendsResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<VKFriendsResponse> call, Response<VKFriendsResponse> response) {
                assert response.body() != null;
                response.body().getResponse().getItems().forEach(element -> {
                    friends.add(new Friend(element.getId(), element.getFirstName(), element.getLastName(),
                            element.getSex(), element.getPhoto50(), element.getPhoto100()));

                });
                TextView friendsCount = (TextView) view.findViewById(R.id.friends_count);
                friendsCount.setText(response.body().getResponse().getCount().toString());
                FriendsAdapter friendsAdapter = new FriendsAdapter(context, friends);
                friendsList.setAdapter(friendsAdapter);
                friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        ListAdapter adapter = friendsList.getAdapter();
//                        int idFriend = (int) friendsList.getItemIdAtPosition(position);

//                        Log.d("FRIEND_ID", String.valueOf(friendsAdapter.getFriendId(position)));
                        Bundle bundle = new Bundle();
                        bundle.putString("friend_id", String.valueOf(friendsAdapter.getFriendId(position)));
                        FriendsAccountFragment friendsAccountFragment = new FriendsAccountFragment();
                        friendsAccountFragment.setArguments(bundle);

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        // Add the second fragment to the layout
                        fragmentTransaction.replace(R.id.fragment_view, friendsAccountFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                });
            }

            @Override
            public void onFailure(Call<VKFriendsResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


        return view;
    }
}