package ru.eltex.api_service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.eltex.api_service.api_service_friends.VKFriendsResponse;
import ru.eltex.api_service.api_service_news.VKNewsResponse;
import ru.eltex.api_service.api_service_user.VKUserResponse;

public interface VKApiService{
    @GET("friends.get?&v=5.131&fields=contacts,sex,photo_100,photo_200_orig,photo_50&order=hints")
    Call<VKFriendsResponse> getFriends(@Query("user_id") Integer userID, @Query("access_token") String token);

    @GET("users.get?&fields=photo_100,sex,status,home_town,bdate&v=5.131")
    Call<VKUserResponse> getUser(@Query("user_ids") String userID, @Query("access_token") String token);

    @GET("newsfeed.get?")
    Call<VKNewsResponse> getNews(@Query("user_id") Integer id, @Query("access_token") String accessToken,
                                 @Query("start_from") String startFrom, @Query("v") Double version);
}
