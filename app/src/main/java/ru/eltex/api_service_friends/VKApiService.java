package ru.eltex.api_service_friends;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VKApiService{
//    @GET("https://api.vk.com/method/friends.get?user_id=181074460&access_token=vk1.a.vTmlg2Ub_lXdjX448RxXp5r5tMxRmzZbJu1ijX1IIpRFiIklfg7-RwJ0Pd7jv38KtBq43Usni7s9YgF1WDT_54x4sa4Im2YwaIo0waJ1V6PbD0NLEFdNTwuMlsJOB6T5boIxGcw9VvlNDANL0okEnG183CTw6u3c0dwkA9jXm8NW2OudKe0V3uX2QNlN9u12&v=5.131&fields=contacts")
    @GET("https://api.vk.com/method/friends.get?user_id=181074460&access_token=vk1.a.vTmlg2Ub_lXdjX448RxXp5r5tMxRmzZbJu1ijX1IIpRFiIklfg7-RwJ0Pd7jv38KtBq43Usni7s9YgF1WDT_54x4sa4Im2YwaIo0waJ1V6PbD0NLEFdNTwuMlsJOB6T5boIxGcw9VvlNDANL0okEnG183CTw6u3c0dwkA9jXm8NW2OudKe0V3uX2QNlN9u12&v=5.131&fields=contacts,sex&order=hints")
    Call<VKResponse> getFriends(@Query("user_id") Integer userID);
}
