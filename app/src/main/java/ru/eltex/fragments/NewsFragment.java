package ru.eltex.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
import ru.eltex.utils.VKApiObject;
import ru.eltex.api_service.news.VKApiServiceNewsImplementation;
import ru.eltex.databinding.FragmentNewsBinding;

/**
 * Фрагмент отображающий новостную ленту
 */
public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private String typeNews = "news";

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            typeNews = getArguments().getString("typeNews");
        }

        if (binding == null) {
            createView();
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * Находит фрагмент по тегу или создает новый и заменяет текущий на созданный или найденный.
     *
     * @param newTypeNews тип новостей на который нужно переключиться
     */
    private void changeTypeNews(String newTypeNews) {
        Fragment newsFragment = getParentFragmentManager().findFragmentByTag(newTypeNews);
        if (newsFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString("typeNews", newTypeNews);
            newsFragment = new NewsFragment();
            newsFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment, newTypeNews).addToBackStack(null).commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, newsFragment).commit();
        }
    }

    /**
     * Если фрагмент не создан, то создает для него отображение
     */
    private void createView() {
        binding = FragmentNewsBinding.inflate(getLayoutInflater());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("access_preference", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "myToken");
        String userId = sharedPreferences.getString("user_id", "myUserId");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        Map<String, IContent> storageContentReceivers = createStorageContentReceivers(token);

        VKApiService vkApiServiceNews = VKApiObject.getInstance().getVKApi();
        VKApiServiceNewsImplementation vkApiServiceNewsImp = new VKApiServiceNewsImplementation(typeNews, token, userId,
                vkApiServiceNews, binding.recyclerView, formatter);

        binding.recyclerView.setLayoutManager(new NewsLinearLayoutManager(getContext(), vkApiServiceNewsImp));
        binding.recyclerView.setAdapter(new NewsAdapter(vkApiServiceNewsImp.getPostList(), getContext(), storageContentReceivers));

        binding.listNews.setOnClickListener(view1 -> {
            changeTypeNews("news");
        });

        binding.listRecommended.setOnClickListener(view1 -> {
            changeTypeNews("recommended");
        });

        vkApiServiceNewsImp.getNewsResponse();
    }

    /**
     * @param token токен пользователя
     * @return Map приемников контента
     */
    private Map<String, IContent> createStorageContentReceivers(String token) {
        Map<String, IContent> storageContentReceivers = new HashMap<>();
        storageContentReceivers.put("photo", new PhotoContent());
        storageContentReceivers.put("video", new VideoContent(token));
        storageContentReceivers.put("link", new LinkContent(getContext()));
        storageContentReceivers.put("doc", new DocContent());
        storageContentReceivers.put("audio", new AudioContent());
        return storageContentReceivers;
    }

}