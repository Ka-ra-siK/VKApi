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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eltex.R;
import ru.eltex.adapters.FriendsAdapter;
import ru.eltex.api_service.VKApiService;
import ru.eltex.utils.VKApiObject;
import ru.eltex.api_service.friends.VKFriendsResponse;
import ru.eltex.instance.Friend;

public class FriendsFragment extends Fragment {

    static List<Friend> friends;
    private final Context context;
    private String token;
    private String userId;
    private String order;
    private Boolean isOnline;
    private static String fields = "contacts,sex,photo_100,photo_200_orig,photo_50,online";
    private static String version = "5.131";
    ArrayList<String> sortingList;

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

        Bundle bundle = getArguments();
        /*
         * Проверяем, получаем ли мы id пользователя через
         * SharedPreferences или через Bundle
         */
        if (bundle != null && !bundle.isEmpty()) {
            userId = bundle.getString("user_id");
        } else {
            userId = sharedPreferences.getString("user_id", "myUserId");
        }

        token = sharedPreferences.getString("token", "myToken");

        //Creating List of friends
        ListView friendsList = (ListView) view.findViewById(R.id.friends_list);

        //Creating field of friends counts
        TextView friendsCount = (TextView) view.findViewById(R.id.friends_count);

        //Creating sorting criteria
        sortingList = new ArrayList<>(Arrays.asList("По важности", "По имени", "Сначала онлайн"));
        String[] sorting = new String[sortingList.size()];
        for (int i = 0; i < sortingList.size(); i++) {
            sorting[i] = sortingList.get(i);
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.sorting_items);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item,
                        sorting);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Create a Listener for sorting selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("По важности")) {
                    order = "hints";
                    isOnline = false;
                } else if (parent.getSelectedItem().toString().equals("По имени")) {
                    order = "name";
                    isOnline = false;
                } else if (parent.getSelectedItem().toString().equals("Сначала онлайн")) {
                    order = "hints";
                    isOnline = true;
                }

                VKApiService vkApiService = VKApiObject.getInstance().getVKApi();

                Log.d("RESPONSE_BODY", order);

                friends = new LinkedList<>();

                //Creating a getFriends request
                vkApiService.getFriends(Integer.valueOf(Objects.requireNonNull(userId)), token,
                        fields, order, Double.valueOf(version)).enqueue(new Callback<VKFriendsResponse>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<VKFriendsResponse> call, Response<VKFriendsResponse> response) {
                        assert response.body() != null;
                        //Creating list of friends

                        response.body().getResponse().getItems().forEach(element -> {
                            //if selected option "Сначала онлайн"
                            if (isOnline) {
                                if (Objects.equals(element.getOnline(), "1")) {
                                    friends.add(new Friend(element.getId(), element.getFirstName(), element.getLastName(),
                                            element.getSex(), element.getPhoto50(), element.getPhoto100(),
                                            element.getOnline(), element.getOnlineMobile()));
                                }
                            } else {
                                friends.add(new Friend(element.getId(), element.getFirstName(), element.getLastName(),
                                        element.getSex(), element.getPhoto50(), element.getPhoto100(),
                                        element.getOnline(), element.getOnlineMobile()));
                            }

                        });

                        //if selected option "Сначала онлайн"
                        if (isOnline) {
                            response.body().getResponse().getItems().forEach(element -> {
                                if (Objects.equals(element.getOnline(), "0")) {
                                    friends.add(new Friend(element.getId(), element.getFirstName(), element.getLastName(),
                                            element.getSex(), element.getPhoto50(), element.getPhoto100(),
                                            element.getOnline(), element.getOnlineMobile()));
                                }
                            });
                        }

                        //Set count of friends
                        friendsCount.setText(response.body().getResponse().getCount().toString());
                        FriendsAdapter friendsAdapter = new FriendsAdapter(context, friends);
                        friendsList.setAdapter(friendsAdapter);

                        //Creating a transition to a friend
                        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}