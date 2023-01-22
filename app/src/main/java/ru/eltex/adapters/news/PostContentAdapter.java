package ru.eltex.adapters.news;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     * Перечисление типов контента отображаемого в ленте
     */
    private enum ItemType {
        PHOTO(1),
        VIDEO(2),
        GIF(3),
        LINK(4),
        AUDIO(5);

        private final int type;

        ItemType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }

    private Map<String, Integer> itemTypes;

    /**
     * Контент в формате класса полученого от VKApi
     */
    List<VKNewsAttachments> content;
    /**
     * Список содержащий загрженные изображения
     */
    private final Map<Integer, Bitmap> images;
    /**
     * Хранилеще приемников контента
     */
    private final Map<String, IContent> storageContentReceivers;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView contentImage;
        private final TextView contentTitle;
        private final TextView contentCaption;
        private final LinearLayout contentView;
        private final VideoView contentGif;
        private final WebView videoView;

        public ViewHolder(View view) {
            super(view);
            this.contentImage = (ImageView) view.findViewById(R.id.content_post);
            this.contentTitle = (TextView) view.findViewById(R.id.content_title);
            this.contentCaption = (TextView) view.findViewById(R.id.content_caption);
            this.contentView = (LinearLayout) view.findViewById(R.id.content_view);
            this.contentGif = (VideoView) view.findViewById(R.id.content_view_gif);
            this.videoView = (WebView) view.findViewById(R.id.content_view_video);
        }

        public ImageView getContentImage() {
            return contentImage;
        }

        public TextView getContentTitle() {
            return contentTitle;
        }

        public TextView getContentCaption() {
            return contentCaption;
        }

        public LinearLayout getContentView() {
            return contentView;
        }

        public VideoView getContentGif() {
            return contentGif;
        }

        public WebView getVideoView() {
            return videoView;
        }
    }

    public PostContentAdapter(List<VKNewsAttachments> content, Map<String, IContent> storageContentReceivers) {
        this.content = content;
        images = new HashMap<>();
        this.storageContentReceivers = storageContentReceivers;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (Objects.equals(content.get(position).getType(), "photo")) {
            type = ItemType.PHOTO.type;
        } else if (Objects.equals(content.get(position).getType(), "video")) {
            type = ItemType.VIDEO.type;
        } else if (Objects.equals(content.get(position).getType(), "doc")) {
            if (content.get(position).getDoc().getType() == 3) {
                type = ItemType.GIF.type;
            }
        } else if (Objects.equals(content.get(position).getType(), "link")) {
            type = ItemType.LINK.type;
        } else if (Objects.equals(content.get(position).getType(), "audio")) {
            type = ItemType.AUDIO.type;
        }
        return type;
    }

    @NonNull
    @Override
    public PostContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_image_item, parent, false);
        if (viewType == ItemType.PHOTO.getType()) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_image_item, parent, false);
        } else if (viewType == ItemType.LINK.getType()) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_link_item, parent, false);
        } else if (viewType == ItemType.GIF.getType()) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_gif_item, parent, false);
        } else if (viewType == ItemType.VIDEO.getType()) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_video_item, parent, false);
        }
        return new PostContentAdapter.ViewHolder(view);
    }

    /**
     * Загрузка и установка контента в зависимости от его содержимого
     */
    @Override
    public void onBindViewHolder(@NonNull PostContentAdapter.ViewHolder holder, int position) {


        VKNewsAttachments vkNewsAttachments = content.get(position);
        IContent contentItemStore = storageContentReceivers.get(vkNewsAttachments.getType());
        String url = "";
        if (contentItemStore != null) {
            url = contentItemStore.getContent(vkNewsAttachments, holder);
        }


        if (!Objects.equals(url, "")) {
            loadImage(url, position, holder);
        }

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    /**
     * Загружает изображения и сохраняет их в map
     *
     * @param url адрес изображения
     * @param position позиция в текущем посте
     * @param holder ссылки на элменты в списке
     */
    private void loadImage(String url, int position, PostContentAdapter.ViewHolder holder) {
        new TaskRunner().executeAsync(new ImageLoadTask(url), (image) -> {
            images.put(position, image);
            holder.getContentImage().setImageBitmap(image);
        });
        holder.getContentImage().setImageBitmap(images.get(position));
    }
}
