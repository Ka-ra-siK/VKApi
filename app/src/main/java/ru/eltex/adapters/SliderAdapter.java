package ru.eltex.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;

public class SliderAdapter extends PagerAdapter {
    private ArrayList<String> images;
    private int imagesUsed = 0;
    Context context;
    // Layout Inflater
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (int) Math.ceil(images.size() / 3.0);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    /**
     * Вывод по 3 изображения в слайдер
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.user_photo_item, container, false);
        ImageView imageViewFirst = (ImageView) itemView.findViewById(R.id.image_view_main_first);
        ImageView imageViewSecond = (ImageView) itemView.findViewById(R.id.image_view_main_second);
        ImageView imageViewThird = (ImageView) itemView.findViewById(R.id.image_view_main_third);
        int imagesCount = images.size();
        int index1 = imagesUsed;
        int index2 = (imagesUsed + 1) % imagesCount;
        int index3 = (imagesUsed + 2) % imagesCount;

        new TaskRunner().executeAsync(new ImageLoadTask(images.get(index1)), (image) -> {
            imageViewFirst.setImageBitmap(image);
        });
        new TaskRunner().executeAsync(new ImageLoadTask(images.get(index2)), (image) -> {
            imageViewSecond.setImageBitmap(image);
        });
        new TaskRunner().executeAsync(new ImageLoadTask(images.get(index3)), (image) -> {
            imageViewThird.setImageBitmap(image);
        });

        if(imagesUsed + 1 >= imagesCount){
            imageViewSecond.setVisibility(View.INVISIBLE);
        }
        if(imagesUsed + 2 >= imagesCount){
            imageViewThird.setVisibility(View.INVISIBLE);
        }

        if(imagesUsed >= imagesCount){
            imagesUsed = 0;
        }

        imagesUsed = imagesUsed + 3;
        // Adding the View
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
