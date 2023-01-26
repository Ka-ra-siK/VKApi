package ru.eltex.adapters.news.content;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.eltex.R;
import ru.eltex.adapters.news.PostContentAdapter;
import ru.eltex.api_service.news.body.items.VKNewsAttachments;
import ru.eltex.api_service.news.link.VKNewsLink;
import ru.eltex.api_service.news.photo.VKNewsPhotoSizes;
import ru.eltex.fragments.LinkFragment;

/**
 * Класс реализующий интерфейс IContent котрый получает изображение для ссылок и устанавливает onClickListener
 * для перехода по ссылкам
 */
public class LinkContent implements IContent {

    private final Context context;

    public LinkContent(Context context) {
        this.context = context;
    }

    @Override
    public String getContent(VKNewsAttachments vkNewsAttachments, PostContentAdapter.ViewHolder holder) {
        VKNewsLink newsLink = vkNewsAttachments.getLink();
        String url = "";
        if (newsLink.getPhoto() != null) {
            for (VKNewsPhotoSizes photoSizes : newsLink.getPhoto().getSizes()) {
                if (photoSizes.getType().equals("y")) {
                    url = photoSizes.getUrl();
                } else if (photoSizes.getType().equals("k") && url.equals("")) {
                    url = photoSizes.getUrl();
                } else if (photoSizes.getType().equals("o") && url.equals("")) {
                    url = photoSizes.getUrl();
                }
            }
        }

        holder.getContentView().setOnClickListener(view -> {
            Bundle bundleLink = new Bundle();
            bundleLink.putString("url", newsLink.getUrl());
            LinkFragment linkFragment = new LinkFragment();
            linkFragment.setArguments(bundleLink);

            ((AppCompatActivity) context)
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_view, linkFragment)
                    .addToBackStack(null)
                    .commit();
        });

        holder.getContentTitle().setText(newsLink.getTitle());
        holder.getContentCaption().setText(newsLink.getCaption());
        return url;
    }
}
