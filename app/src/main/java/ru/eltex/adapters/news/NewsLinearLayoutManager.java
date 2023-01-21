package ru.eltex.adapters.news;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.eltex.api_service.news.VKApiServiceNewsImplementation;

public class NewsLinearLayoutManager extends LinearLayoutManager {


    private VKApiServiceNewsImplementation vkApiServiceNewsImp;

    public NewsLinearLayoutManager(Context context, VKApiServiceNewsImplementation vkApiServiceNewsImp) {
        super(context);
        this.vkApiServiceNewsImp = vkApiServiceNewsImp;
    }

    /**
     * В случае если отображается предпоследний элемент загруженного списка, запрашивает следующую часть.
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        final int result = super.scrollVerticallyBy(dy, recycler, state);

        if (findLastVisibleItemPosition() == getItemCount() - 1 && !vkApiServiceNewsImp.isUpdate()) {
            vkApiServiceNewsImp.setUpdate(true);
            vkApiServiceNewsImp.getNewsResponse();
        }

        return result;

    }
}
