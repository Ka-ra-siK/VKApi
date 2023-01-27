package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.news.NewsAdapter;
import ru.eltex.adapters.news.NewsLinearLayoutManager;
import ru.eltex.adapters.news.content.AudioContent;
import ru.eltex.adapters.news.content.DocContent;
import ru.eltex.adapters.news.content.IContent;
import ru.eltex.adapters.news.content.LinkContent;
import ru.eltex.adapters.news.content.PhotoContent;
import ru.eltex.adapters.news.content.VideoContent;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.news.VKApiServiceNewsImplementation;
import ru.eltex.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private final Context context;
    private FragmentNewsBinding binding;

    private static final Map<String, Parcelable> save = new HashMap<>();

    public NewsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String typeNews = "news";
        if (getArguments() != null) {
            typeNews = getArguments().getString("typeNews");
        }


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        binding = FragmentNewsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        String token = sharedPreferences.getString("token", "myToken");
        String userId = sharedPreferences.getString("user_id", "myUserId");

        Map<String, IContent> storageContentReceivers = new HashMap<>();
        storageContentReceivers.put("photo", new PhotoContent());
        storageContentReceivers.put("video", new VideoContent(token));
        storageContentReceivers.put("link", new LinkContent(getContext()));
        storageContentReceivers.put("doc", new DocContent());
        storageContentReceivers.put("audio", new AudioContent());

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceNews = retrofit.create(VKApiService.class);
        RecyclerView recyclerView = binding.recyclerView;
        VKApiServiceNewsImplementation vkApiServiceNewsImp = new VKApiServiceNewsImplementation(typeNews, token, userId,
                vkApiServiceNews,
                recyclerView, formatter);

        recyclerView.setLayoutManager(new NewsLinearLayoutManager(context, vkApiServiceNewsImp));
        recyclerView.setAdapter(new NewsAdapter(vkApiServiceNewsImp.getPostList(), context, storageContentReceivers));

        binding.listNews.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("typeNews", "news");
            NewsFragment newsFragment = new NewsFragment(context);
            newsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment).commit();
        });

        binding.listRecommended.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("typeNews", "recommended");
            NewsFragment newsFragment = new NewsFragment(context);
            newsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment).commit();
        });

        vkApiServiceNewsImp.getNewsResponse();

        // Inflate the layout for this fragment
        return view;
    }

}