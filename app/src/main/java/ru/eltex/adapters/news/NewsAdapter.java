package ru.eltex.adapters.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.adapters.news.content.IContent;
import ru.eltex.instance.Post;

/**
 * Адаптер для отображения новостной ленты
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    /**
     * Список содержащий классы с контентом для отображения каждой отдельной записи из новостной ленты.
     */
    private final List<Post> vkNewsPosts;
    /**
     * Контекст приложения
     */
    private final Context context;
    /**
     * Хранилеще приемников контента
     */
    private final Map<String, IContent> storageContentReceivers;

    /**
     * Класс наследуемый от RecyclerView.ViewHolder и дополненый необходимыми полями для отображения записей.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Аватар автора записи
         */
        private final ImageView avatarAuthorView;
        /**
         * Имя автора записи
         */
        private final TextView nameAuthorView;
        /**
         * Дата публикации
         */
        private final TextView datePostView;
        /**
         * Текст записи
         */
        private final TextView textNewsView;
        /**
         * Контент записи (видео, изображение)
         */
        private final RecyclerView contentView;

        /**
         * Количество лайков
         */
        private final TextView likesView;
        /**
         * Количество репостов
         */
        private final TextView repostView;

        public ViewHolder(View view) {
            super(view);
            this.textNewsView = (TextView) view.findViewById(R.id.text_news);
            this.contentView = (RecyclerView) view.findViewById(R.id.recyclerView_content);
            this.avatarAuthorView = (ImageView) view.findViewById(R.id.author_avatar);
            this.nameAuthorView = (TextView) view.findViewById(R.id.name_author);
            this.likesView = (TextView) view.findViewById(R.id.likes_count);
            this.repostView = (TextView) view.findViewById(R.id.reposts_count);
            this.datePostView = (TextView) view.findViewById(R.id.date_post);

        }

        public TextView getTextNewsView() {
            return textNewsView;
        }

        public RecyclerView getContentView() {
            return contentView;
        }

        public ImageView getAvatarAuthorView() {
            return avatarAuthorView;
        }

        public TextView getNameAuthorView() {
            return nameAuthorView;
        }

        public TextView getLikesView() {
            return likesView;
        }

        public TextView getRepostView() {
            return repostView;
        }

        public TextView getDatePostView() {
            return datePostView;
        }
    }

    public NewsAdapter(List<Post> vkNewsPosts, Context context, Map<String, IContent> storageContentReceivers) {
        this.vkNewsPosts = vkNewsPosts;
        this.context = context;
        this.storageContentReceivers = storageContentReceivers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_news_item,
                parent, false);
        return new ViewHolder(view);
    }

    /**
     * Заполение контентом отображение списка.
     * Для загрузки аватраов используется ImageLoadTask
     * Создается новый список для отображения контента (изображение, видео).
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post curPost = vkNewsPosts.get(position);

        new TaskRunner().executeAsync(new ImageLoadTask(curPost.getAuthor().getPhoto100()), (image) -> {
            holder.getAvatarAuthorView().setImageBitmap(image);
        });
        holder.getNameAuthorView().setText(curPost.getAuthor().getName());
        holder.getDatePostView().setText(curPost.getDate());
        holder.getTextNewsView().setText(curPost.getTextPost());

        //TODO Сделать асимметричный вывод изображений.
        holder.getContentView().setAdapter(new PostContentAdapter(curPost.getContent(), storageContentReceivers));
        holder.getContentView().setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));

        holder.getLikesView().setText("Likes: " + curPost.getLikes());
        holder.getRepostView().setText("Reposts: " + curPost.getReposts());
    }

    @Override
    public int getItemCount() {
        return vkNewsPosts.size();
    }
}
