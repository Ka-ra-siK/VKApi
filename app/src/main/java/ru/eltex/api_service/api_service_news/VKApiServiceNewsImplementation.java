package ru.eltex.api_service.api_service_news;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.api_service_news.body.groups.VKNewsGroups;
import ru.eltex.api_service.api_service_news.body.items.VKNewsItems;
import ru.eltex.api_service.api_service_news.body.profiles.VKNewsProfiles;
import ru.eltex.instance.Post;
import ru.eltex.instance.authors.Author;
import ru.eltex.instance.authors.GroupAuthor;
import ru.eltex.instance.authors.ProfileAuthor;

/**
 * Обработка информации полученой из запроса
 */
public class VKApiServiceNewsImplementation {

    /**
     * Список записей из новостной ленты
     */
    private final List<Post> postList;
    /**
     * Информация о местоположении следующего списка новостей
     */
    private String startFrom;
    /**
     * Map<ID автора, Author>
     */
    Map<Integer, Author> authorsMap;
    /**
     * ID пользователя, время жизни токена и токен
     */
    Map<String, String> params;
    /**
     * Класс созданный Retrofit и содержащий запрос на получение списка новостей
     */
    private final VKApiService vkApiServiceNews;
    /**
     * RecyclerView с списком новосей
     */
    RecyclerView recyclerViewNews;
    /**
     * Формат даты
     */
    SimpleDateFormat formatter;

    /**
     * User token
     */
    private String token;
    /**
     * ID of user
     */
    private String userId;

    /**
     * Индикатор обновления списка
     */
    boolean update;

    public VKApiServiceNewsImplementation(String token, String userID, VKApiService vkApiServiceNews,
                                          RecyclerView recyclerView, SimpleDateFormat formatter) {

        this.postList = new LinkedList<>();
        this.startFrom = "";
        this.authorsMap = new HashMap<>();
//        this.params = params;
        this.token = token;
        this.userId = userID;
        this.vkApiServiceNews = vkApiServiceNews;
        this.recyclerViewNews = recyclerView;
        this.formatter = formatter;
        this.update = false;
    }

    /**
     * Получение новостей от VKApi и создание списка классов Post
     */
    public void getNewsResponse() {
        vkApiServiceNews.getNews(Integer.valueOf(Objects.requireNonNull(userId)), token, startFrom, 5.131)
                .enqueue(new Callback<VKNewsResponse>() {
                             @Override
                             public void onResponse(@NonNull Call<VKNewsResponse> call, @NonNull Response<VKNewsResponse> response) {
                                 assert response.body() != null;
                                 VKNewsResponseBody responseBody = response.body().getResponse();

                                 authorsMap = getMapAuthors(responseBody.getGroups(), responseBody.getProfiles());

                                 int sizePostList = recyclerViewNews.getAdapter().getItemCount();
                                 startFrom = responseBody.getNextFrom();
                                 List<VKNewsItems> vkNewsItemsList = responseBody.getItems();

                                 for (VKNewsItems vkNewsItems : vkNewsItemsList) {
                                     if (vkNewsItems.getLikes() != null) {

                                         Date date = new java.util.Date(vkNewsItems.getDate() * 1000L);

                                         postList.add(new Post(vkNewsItems.getPostID(), authorsMap.get(vkNewsItems.getSourceID()),
                                                 formatter.format(date.getTime()), vkNewsItems.getTextNewsItem(), vkNewsItems.getAttachments(),
                                                 vkNewsItems.getLikes().getCount(), vkNewsItems.getReposts().getCount()
                                         ));
                                     }
                                 }

                                 Objects.requireNonNull(recyclerViewNews.getAdapter())
                                         .notifyItemRangeInserted(sizePostList, postList.size());
                                 setUpdate(false);


                             }

                             @Override
                             public void onFailure(Call<VKNewsResponse> call, Throwable t) {
                                 System.out.println(t.getMessage());
                             }
                         }

                );
    }

    public List<Post> getPostList() {
        return postList;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @param groups   Списк групп
     * @param profiles Список людей
     * @return Map пары ID, Автор
     */
    private Map<Integer, Author> getMapAuthors(List<VKNewsGroups> groups, List<VKNewsProfiles> profiles) {
        Map<Integer, Author> ownAuthorsMap = new HashMap<>();
        for (VKNewsGroups vkNewsGroup : groups) {
            GroupAuthor groupAuthor = new GroupAuthor(vkNewsGroup.getId(), vkNewsGroup.getName(),
                    vkNewsGroup.getPhoto50(), vkNewsGroup.getPhoto100(), vkNewsGroup.getPhoto200());
            ownAuthorsMap.put(vkNewsGroup.getId() * -1, groupAuthor);
        }
        for (VKNewsProfiles vkNewsProfile : profiles) {
            ProfileAuthor profileAuthor = new ProfileAuthor(vkNewsProfile.getId(),
                    vkNewsProfile.getFirstName(), vkNewsProfile.getLastName(), vkNewsProfile.getSex(),
                    vkNewsProfile.getPhoto50(), vkNewsProfile.getPhoto100());
            ownAuthorsMap.put(vkNewsProfile.getId(), profileAuthor);
        }
        return ownAuthorsMap;

    }
}
