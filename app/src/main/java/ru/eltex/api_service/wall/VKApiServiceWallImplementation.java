package ru.eltex.api_service.wall;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.eltex.api_service.VKApiService;
import ru.eltex.api_service.news.VKNewsResponse;
import ru.eltex.api_service.news.VKNewsResponseBody;
import ru.eltex.api_service.news.body.groups.VKNewsGroups;
import ru.eltex.api_service.news.body.items.VKNewsItems;
import ru.eltex.api_service.news.body.profiles.VKNewsProfiles;
import ru.eltex.instance.Post;
import ru.eltex.instance.authors.Author;
import ru.eltex.instance.authors.GroupAuthor;
import ru.eltex.instance.authors.ProfileAuthor;

public class VKApiServiceWallImplementation {

    private final List<Post> postList;
    private String startFrom;
    Map<Integer, Author> authorsMap;

    private final VKApiService vkApiServiceNews;
    RecyclerView recyclerViewNews;
    SimpleDateFormat formatter;
    private String token;
    private String userId;
    private final String typeNews;
    boolean update;

    public VKApiServiceWallImplementation(List<Post> postList, String startFrom, Map<Integer, Author> authorsMap,
                                          VKApiService vkApiServiceNews, RecyclerView recyclerViewNews,
                                          SimpleDateFormat formatter,
                                          String token, String userId, String typeNews, boolean update) {
        this.postList = postList;
        this.startFrom = startFrom;
        this.authorsMap = authorsMap;
        this.vkApiServiceNews = vkApiServiceNews;
        this.recyclerViewNews = recyclerViewNews;
        this.formatter = formatter;
        this.token = token;
        this.userId = userId;
        this.typeNews = typeNews;
        this.update = update;
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

//    public void getWallResponse() {
//        vkApiServiceNews.getNews(typeNews, Integer.valueOf(Objects.requireNonNull(userId)), token, startFrom, 5.131)
//                .enqueue(new Callback<VKWallResponse>() {
//                             @Override
//                             public void onResponse(@NonNull Call<VKWallResponse> call, @NonNull Response<VKWallResponse> response) {
//                                 assert response.body() != null;
//                                 VKWallResponseBody responseBody = response.body().getResponse();
//
//                                 authorsMap = getMapAuthors(responseBody.getGroups(), responseBody.getProfiles());
//
//                                 int sizePostList = recyclerViewNews.getAdapter().getItemCount();
//                                 startFrom = responseBody.getNextFrom();
//                                 List<VKNewsItems> vkNewsItemsList = responseBody.getItems();
//
//                                 for (VKNewsItems vkNewsItems : vkNewsItemsList) {
//                                     if (vkNewsItems.getLikes() != null) {
//
//                                         Date date = new java.util.Date(vkNewsItems.getDate() * 1000L);
//
//                                         postList.add(new Post(vkNewsItems.getPostID(), authorsMap.get(vkNewsItems.getSourceID()),
//                                                 formatter.format(date.getTime()), vkNewsItems.getTextNewsItem(), vkNewsItems.getAttachments(),
//                                                 vkNewsItems.getLikes().getCount(), vkNewsItems.getReposts().getCount()
//                                         ));
//                                     }
//                                 }
//
//                                 Objects.requireNonNull(recyclerViewNews.getAdapter())
//                                         .notifyItemRangeInserted(sizePostList, postList.size());
//                                 setUpdate(false);
//
//
//                             }
//
//                             @Override
//                             public void onFailure(Call<VKWallResponse> call, Throwable t) {
//                                 System.out.println(t.getMessage());
//                             }
//                         }
//
//                );
//    }

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
