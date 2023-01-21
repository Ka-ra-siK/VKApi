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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;
import java.util.Objects;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;
import ru.eltex.fragments.FriendsAccountFragment;
import ru.eltex.instance.Friend;

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private final Context context;
    private final List<Friend> friends;
    private String friendId;

    public FriendsAdapter(Context context, List<Friend> friends) {
        super(context, R.layout.list_friends, friends);
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.list_friends, parent, false);

        //Set first name of friend
        TextView firstName = view.findViewById(R.id.first_name);
        firstName.setText((this.friends.get(position)).getFirstName());

        //Set second name of friend
        TextView lastName = view.findViewById(R.id.last_name);
        lastName.setText((this.friends.get(position)).getLastName());

        //Set image of friend depending on sex
        ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        ImageView isOnlineImage = (ImageView) view.findViewById(R.id.is_online);
        new TaskRunner().executeAsync(new ImageLoadTask(this.friends.get(position).getPhoto50()), (image) -> {
            avatar.setImageBitmap(image);
            Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            avatar.setImageDrawable(roundedBitmapDrawable);
        });

        if (Objects.equals(this.friends.get(position).getOnline(), "1")){
            if (this.friends.get(position).getOnlineMobile() != null){
                isOnlineImage.setImageResource(R.drawable.phone);
            }else {
                isOnlineImage.setImageResource(R.drawable.is_online);
            }
        }


        return view;
    }

    public String getFriendId(int position) {
        return this.friends.get(position).getId();
    }
}
