package ru.eltex.api_service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.eltex.api_service.friends.VKFriendsResponse;
import ru.eltex.api_service.groups.VKGroupsResponse;
import ru.eltex.api_service.news.VKNewsResponse;
import ru.eltex.api_service.user.VKUserResponse;
import ru.eltex.api_service.wall.VKWallResponse;

public interface VKApiService {

    //Request to show all friends
    //TODO clear fields
    @GET("friends.get?")
    Call<VKFriendsResponse> getFriends(@Query("user_id") Integer userID, @Query("access_token") String token,
                                       @Query("fields") String fields, @Query("order") String order,
                                       @Query("v") Double version);

    //Request to show user account
    //TODO clear fields
    @GET("users.get?&fields=photo_100,sex,status,home_town,bdate,online&v=5.131")
    Call<VKUserResponse> getUser(@Query("user_ids") String userID, @Query("access_token") String token);

    //Request to show users news
    @GET("newsfeed.{type_news}?")
    Call<VKNewsResponse> getNews(@Path("type_news") String typeNews, @Query("user_id") Integer id, @Query("access_token") String accessToken,
                                 @Query("start_from") String startFrom, @Query("v") Double version);

    //Request to show users news
    @GET("newsfeed.{type_news}?")
    Call<VKNewsResponse> getNews(@Path("type_news") String typeNews, @Query("user_id") Integer id, @Query("access_token") String accessToken,
                                 @Query("v") Double version);

    //Request to show users wall
    @GET("wall.get?")
    Call<VKWallResponse> getWall(@Query("user_id") String id, @Query("access_token") String accessToken,
                                 @Query("v") Double version);

    @GET("groups.get?")
    Call<VKGroupsResponse> getGroups(@Query("user_id") String id, @Query("access_token") String accessToken,
                                     @Query("filter") String filters, @Query("extended") Integer extended,
                                     @Query("fields") String fields, @Query("v") Double version);
}
