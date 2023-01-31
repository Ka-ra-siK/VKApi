package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import ru.eltex.api_service.VKApiService;
import ru.eltex.utils.VKApiObject;
import ru.eltex.databinding.FragmentWallBinding;

public class WallFragment extends Fragment {

    private String token;
    private String userId;
    private FragmentWallBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWallBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        VKApiService vkApiServiceWall = VKApiObject.getInstance().getVKApi();

        RecyclerView recyclerView = binding.recyclerView;

        // Inflate the layout for this fragment
        return view;
    }
}