package ru.eltex.api_service_friends;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApiService{
    @GET("friends.get/?&access_token=vk1.a.yNTzB8u__oF3Y8-hYIP5k83lWe7LVGos_7kpnwkyxVL0ctfJBT0jBkna0QM8IYPjQTPeh4CdStY34zddYGF8_s7VNA5Z2KhqeSATCh3luAyxNdyik6Cov90uG1f7cQukTvRKfcaBtPsZU_gykNOwAmbRdmoLcU3vDhfT5-4pqDTVI1YwB_IRQKzu81Y8n3gb&v=5.131&fields=contacts")
    Call<VKResponse> getFriends(@Query("user_id") Integer userID);
}
