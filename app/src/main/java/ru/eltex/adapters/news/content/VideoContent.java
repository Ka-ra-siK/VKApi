package ru.eltex.adapters.news.content;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.adapters.news.PostContentAdapter;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.WebClient;
import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.errors.VKError;
import ru.eltex.api_service.news.video.VKNewsVideo;
import ru.eltex.api_service.video.VKVideoResponse;

/**
 * Класс реализующий интерфейс IContent для получения url видео и отображения его в WebView
 */
public class VideoContent implements IContent {

    private final String token;

    public VideoContent(String token) {
        this.token = token;
    }

    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments, PostContentAdapter.ViewHolder holder) {

        VKNewsVideo newsVideo = vkNewsAttachments.getVideo();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        VKApiService vkApiServiceVideo = retrofit.create(VKApiService.class);

        vkApiServiceVideo.getVideo(newsVideo.getOwnerID(), token,
                -newsVideo.getOwnerID() + "_" + newsVideo.getId(), 5.131).enqueue(new Callback<VKVideoResponse>() {
            @Override
            public void onResponse(Call<VKVideoResponse> call, Response<VKVideoResponse> response) {
                assert response.body() != null;
                try {
                    VKNewsVideo video = response.body().getResponse().getItems().get(0);
                    holder.getVideoView().getSettings().setJavaScriptEnabled(true);
                    holder.getVideoView().setWebViewClient(new WebClient(holder.getVideoView().getContext()));
                    holder.getVideoView().loadUrl(video.getPlayer());
                } catch (NullPointerException e){
                    VKError error = response.body().getError();
                    Log.d("GET_VIDEO","Error code: " + error.getErrorCode());
                    Log.e("GET_VIDEO", "Error msg: " + error.getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<VKVideoResponse> call, Throwable t) {
                Log.d("GET_VIDEO","onFailure()");
                Log.e("GET_VIDEO", String.valueOf(t));
            }
        });

        return "";
    }
}
