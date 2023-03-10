package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eltex.R;
import ru.eltex.adapters.GroupsAdapter;
import ru.eltex.api_service.VKApiService;
import ru.eltex.utils.VKApiObject;
import ru.eltex.api_service.groups.VKGroup;
import ru.eltex.api_service.groups.VKGroupsResponse;

/**
 * Фрагмент отвечающий за получение групп и подготовку к отображению
 */
public class GroupsFragment extends Fragment {

    /**
     * Список групп пользователя
     */
    static List<VKGroup> groups;
    private String token;
    private String userId;
    /**
     * Какие типы групп отображать
     */
    private String filter;
    /**
     * Поля отображаемые в информации о группах
     */
    private String fields;

    public GroupsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "myToken");

        Bundle bundle = getArguments();
        /*
         * Проверяем, получаем ли мы id пользователя через
         * SharedPreferences или через Bundle
         */
        if (bundle != null) {
            userId = bundle.getString("user_id");
            filter = bundle.getString("filter");
            fields = bundle.getString("fields");
        } else {
            userId = sharedPreferences.getString("user_id", "myUserId");
        }

        ListView groupList = (ListView) view.findViewById(R.id.groups_list);
        groups = new LinkedList<>();


        VKApiService vkApiService = VKApiObject.getInstance().getVKApi();

        vkApiService.getGroups(userId, token, filter, 1, fields, 5.131).enqueue(new Callback<VKGroupsResponse>() {
            @Override
            public void onResponse(Call<VKGroupsResponse> call, Response<VKGroupsResponse> response) {
                assert response.body() != null;
                groups.addAll(response.body().getResponse().getItems());

                TextView groupsCount = (TextView) view.findViewById(R.id.groups_count);
                groupsCount.setText(response.body().getResponse().getCount().toString());
                GroupsAdapter groupsAdapter = new GroupsAdapter(view.getContext(), groups);
                groupList.setAdapter(groupsAdapter);
            }

            @Override
            public void onFailure(Call<VKGroupsResponse> call, Throwable t) {
                Log.d("GET_GROUPS", "onFailure()");
                Log.e("GET_GROUPS", String.valueOf(t));
            }
        });

        return view;
    }
}
