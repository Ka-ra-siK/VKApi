package ru.eltex.api_service_user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApiServiceUser {
    @GET("https://api.vk.com/method/account.getProfileInfo?&v=5.131")
    Call<VKResponseUser> getUser(@Query("user_id") Integer userID, @Query("access_token") String token);
}
