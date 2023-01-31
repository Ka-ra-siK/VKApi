package ru.eltex.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.eltex.api_service.VKApiService;

/**
 * Создет единственный экземпляр класса VKApiService
 */
public class VKApiObject {

    private static VKApiObject vkApiObject;
    private VKApiService vkApiService;

    private VKApiObject() {
        vkApiService = createService();
    }

    /**
     * Создание объекта клсса VKApiService
     *
     * @return объект класса VKApiService для работы с Api VK.
     */
    private VKApiService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.vk.com/method/")
                .build();

        return retrofit.create(VKApiService.class);
    }

    /**
     * Гарантирует создание только одного объекта класса VKApiService
     *
     * @return объект содержащий VKApiService
     */
    public static VKApiObject getInstance() {
        if (vkApiObject == null) {
            vkApiObject = new VKApiObject();
        }
        return vkApiObject;
    }

    /**
     * @return VKApiService
     */
    public VKApiService getVKApi() {
        return vkApiObject.vkApiService;
    }
}
