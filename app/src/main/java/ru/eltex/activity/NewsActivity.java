package ru.eltex.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.R;
import ru.eltex.adapters.NewsAdapter;
import ru.eltex.adapters.NewsLinearLayoutManager;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_news.VKApiServiceNewsImplementation;


/**
 * Класс отвечающий за создание спика отображающего новостную ленту
 */
public class NewsActivity extends AppCompatActivity {


    /**
     * При переходе на NewsActivity происходит создание объекта класса Retrofit и создание
     * с его помощью VKApiServiceNews, который выполняет запросы к API.
     * Создание RecyclerView отображающего ленту новостей.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String url = (String) getIntent().getSerializableExtra("URL");
        Map<String, String> params = getParamsUrl(url);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceNews = retrofit.create(VKApiService.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        VKApiServiceNewsImplementation vkApiServiceNewsImp = new VKApiServiceNewsImplementation(params,
                vkApiServiceNews, recyclerView, formatter);

        recyclerView.setLayoutManager(new NewsLinearLayoutManager(getApplicationContext(), vkApiServiceNewsImp));
        recyclerView.setAdapter(new NewsAdapter(vkApiServiceNewsImp.getPostList(), getApplicationContext()));

        vkApiServiceNewsImp.getNewsResponse();

    }


    /**
     * Разбивает url на пары <Ключ, Значение>
     *
     * @param url адрес содержащий в себе: access_token, user_id, expires_in.
     * @return Map<String, String>
     */
    public Map<String, String> getParamsUrl(String url) {
        Map<String, String> params = new HashMap<>();
        int startParam = url.indexOf("#") + "#".length();
        String subUrl = url.substring(startParam);
        for (String param : subUrl.split("&")) {
            String[] keyAndParam = param.split("=");
            params.put(keyAndParam[0], keyAndParam[1]);
        }
        return params;
    }
}
