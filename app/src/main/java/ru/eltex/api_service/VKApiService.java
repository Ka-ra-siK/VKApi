package ru.eltex.api_service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.eltex.api_service.api_service_friends.VKResponseFriends;
import ru.eltex.api_service.api_service_news.VKNewsResponse;
import ru.eltex.api_service.api_service_user.VKResponseUser;

public interface VKApiService{
    @GET("https://api.vk.com/method/friends.get?&v=5.131&fields=contacts,sex&order=hints")
    Call<VKResponseFriends> getFriends(@Query("user_id") Integer userID, @Query("access_token") String token);

    @GET("https://api.vk.com/method/account.getProfileInfo?&v=5.131")
    Call<VKResponseUser> getUser(@Query("user_id") Integer userID, @Query("access_token") String token);

    @GET("newsfeed.get?")
    Call<VKNewsResponse> getNews(@Query("user_id") Integer id, @Query("access_token") String accessToken,
                                 @Query("start_from") String startFrom, @Query("v") Double version);
}
