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

public class NewsFragment extends Fragment {

    private String token;
    private String userId;
    private final Context context;

    public NewsFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Get a SharedPreferences instance
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);

        token = sharedPreferences.getString("token", "myToken");
        userId = sharedPreferences.getString("user_id", "myUserId");

//        setContentView(R.layout.activity_news);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        String url = (String) getIntent().getSerializableExtra("URL");
//        Map<String, String> params = getParamsUrl(url);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceNews = retrofit.create(VKApiService.class);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


        VKApiServiceNewsImplementation vkApiServiceNewsImp = new VKApiServiceNewsImplementation(token, userId,
                vkApiServiceNews,
                recyclerView, formatter);

        recyclerView.setLayoutManager(new NewsLinearLayoutManager(context, vkApiServiceNewsImp));
        recyclerView.setAdapter(new NewsAdapter(vkApiServiceNewsImp.getPostList(), context));

        vkApiServiceNewsImp.getNewsResponse();


        // Inflate the layout for this fragment
        return view;
    }
}