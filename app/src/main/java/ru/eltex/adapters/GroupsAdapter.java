package ru.eltex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.List;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.api_service.groups.VKGroup;

/**
 * Адаптер для отображения списка групп пользователя
 */
public class GroupsAdapter extends ArrayAdapter<VKGroup> {
    private final Context context;
    private final List<VKGroup> groups;

    public GroupsAdapter(Context context, List<VKGroup> groups) {
        super(context, R.layout.list_groups_item, groups);
        this.context = context;
        this.groups = groups;
    }

    /**
     * Устанавливает для отображения информацию для каждой группы
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.list_groups_item, parent, false);

        TextView nameGroup = view.findViewById(R.id.name_group);
        nameGroup.setText((this.groups.get(position).getName()));

        TextView membersCount = view.findViewById(R.id.members_count);
        membersCount.setText(String.valueOf(this.groups.get(position).getMembersCount()));

        ImageView imageView = (ImageView) view.findViewById(R.id.avatar_group);
        new TaskRunner().executeAsync(new ImageLoadTask(this.groups.get(position).getPhoto50()), (image) -> {
            imageView.setImageBitmap(image);
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            imageView.setImageDrawable(roundedBitmapDrawable);
        });

        return view;
    }
}
