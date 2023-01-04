package ru.eltex.api_service_friends;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApiService{
    @GET("https://api.vk.com/method/friends.get?&v=5.131&fields=contacts,sex&order=hints")
    Call<VKResponse> getFriends(@Query("user_id") Integer userID, @Query("access_token") String token);
}
