package ru.eltex.api_service_news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApiServiceNews {

    @GET("newsfeed.get?")
    Call<VKNewsResponse> getNews(@Query("user_id") Integer id, @Query("access_token") String accessToken,
                                 @Query("start_from") String startFrom, @Query("v") Double version);
}
