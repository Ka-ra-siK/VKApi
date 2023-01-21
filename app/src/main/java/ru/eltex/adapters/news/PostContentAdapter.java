package ru.eltex.adapters.news;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.adapters.news.content.AudioContent;
import ru.eltex.adapters.news.content.DocContent;
import ru.eltex.adapters.news.content.IContent;
import ru.eltex.adapters.news.content.LinkContent;
import ru.eltex.adapters.news.content.PhotoContent;
import ru.eltex.adapters.news.content.VideoContent;
import ru.eltex.api_service.news.body.items.VKNewsAttachments;

public class PostContentAdapter extends RecyclerView.Adapter<PostContentAdapter.ViewHolder> {

    /**
     * Контент в формате класса полученого от VKApi
     */
    List<VKNewsAttachments> content;
    /**
     * Список содержащий загрженные изображения
     */
    private final Map<Integer, Bitmap> images;

    /**
     * Хранилище способов отображения контента в зависимости от содержимого
     */
    private final Map<String, IContent> getContentStore;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView contentView;

        public ViewHolder(View view) {
            super(view);
            this.contentView = (ImageView) view.findViewById(R.id.content_post);
        }

        public ImageView getContentView() {
            return contentView;
        }
    }

    public PostContentAdapter(List<VKNewsAttachments> content) {
        this.content = content;
        images = new HashMap<>();
        getContentStore = new HashMap<>();
        getContentStore.put("photo", new PhotoContent());
        getContentStore.put("video", new VideoContent());
        getContentStore.put("link", new LinkContent());
        getContentStore.put("doc", new DocContent());
        getContentStore.put("audio", new AudioContent());
    }

    @NonNull
    @Override
    public PostContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_image_item, parent, false);
        return new PostContentAdapter.ViewHolder(view);
    }

    /**
     * Загрузка и установка изображений
     */
    @Override
    public void onBindViewHolder(@NonNull PostContentAdapter.ViewHolder holder, int position) {

        VKNewsAttachments vkNewsAttachments = content.get(position);
        String url = getContentStore.get(vkNewsAttachments.getType()).getContent(vkNewsAttachments);

        new TaskRunner().executeAsync(new ImageLoadTask(url), (image) -> {
            images.put(position, image);
            holder.getContentView().setImageBitmap(image);
        });
        holder.getContentView().setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }
}
