package ru.eltex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import ru.eltex.R;
import ru.eltex.friends.Friend;

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private final Context context;
    private final List<Friend> friends;

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

        TextView firstName = view.findViewById(R.id.first_name);
        firstName.setText((this.friends.get(position)).getFirstName());

        TextView lastName = view.findViewById(R.id.last_name);
        lastName.setText((this.friends.get(position)).getLastName());

        ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
        if ((Objects.equals(this.friends.get(position).getSex(), "1"))) {
            imageView.setImageResource(R.drawable.woman);
        } else {
            imageView.setImageResource(R.drawable.man);
        }

        return view;
    }
}
