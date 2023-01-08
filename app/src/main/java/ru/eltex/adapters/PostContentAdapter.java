package ru.eltex.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.api_service_news.body.items.VKNewsAttachments;
import ru.eltex.api_service_news.photo.VKNewsPhotoSizes;
import ru.eltex.api_service_news.video.VKNewsVideoImage;

public class PostContentAdapter extends RecyclerView.Adapter<PostContentAdapter.ViewHolder> {

    /**
     * Контент в формате класса полученого от VKApi
     */
    List<VKNewsAttachments> content;
    /**
     * Список содержащий загрженные изображения
     */
    List<Bitmap> images;

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
        images = new ArrayList<>();
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

        String url = "";

        if (content.get(position).getType().equals("photo")) {
            for (VKNewsPhotoSizes photoSizes : content.get(position).getPhoto().getSizes()) {
                if (photoSizes.getType().equals("z")) {
                    url = photoSizes.getUrl();
                } else if (photoSizes.getType().equals("x") && url.equals("")) {
                    url = photoSizes.getUrl();
                }
            }
        }
        if (content.get(position).getType().equals("video")) {
            int sizeNewsVideoImage = content.get(position).getVideo().getImage().size();
            VKNewsVideoImage newsVideoImage = content.get(position).getVideo().getImage().get(sizeNewsVideoImage - 1);
            url = newsVideoImage.getUrl();
        }

        if (!Objects.equals(url, "")) {
            try {
                images.add(new ImageLoadTask(url, holder.getContentView()).execute().get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            holder.getContentView().setImageBitmap(images.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return content.size();
    }
}
