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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.NewsAdapter;
import ru.eltex.adapters.NewsLinearLayoutManager;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_news.VKApiServiceNewsImplementation;
import ru.eltex.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private String token;
    private String userId;
    private final Context context;
    private FragmentNewsBinding binding;
    private VKApiServiceNewsImplementation vkApiServiceNewsImp;

    public NewsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String typeNews = "get";
        if ( getArguments() != null) {
            typeNews =  getArguments().getString("typeNews");
        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        binding = FragmentNewsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceNews = retrofit.create(VKApiService.class);
        RecyclerView recyclerView = binding.recyclerView;
        vkApiServiceNewsImp = new VKApiServiceNewsImplementation(typeNews, token, userId,
                vkApiServiceNews,
                recyclerView, formatter);

        recyclerView.setLayoutManager(new NewsLinearLayoutManager(context, vkApiServiceNewsImp));
        recyclerView.setAdapter(new NewsAdapter(vkApiServiceNewsImp.getPostList(), context));

        binding.listNews.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("typeNews", "get");
            NewsFragment newsFragment = new NewsFragment(context);
            newsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment).commit();
        });

        binding.listRecommended.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("typeNews", "getRecommended");
            NewsFragment newsFragment = new NewsFragment(context);
            newsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment).commit();
        });

        vkApiServiceNewsImp.getNewsResponse();

        // Inflate the layout for this fragment
        return view;
    }

}