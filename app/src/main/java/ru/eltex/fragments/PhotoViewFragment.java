package ru.eltex.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.eltex.ImageLoadTask;
import ru.eltex.R;
import ru.eltex.TaskRunner;


public class PhotoViewFragment extends Fragment {

    private String photoId;
    ImageView userImgView;
    ImageView backImage;
    ImageView likeImage;
    ImageView repostImage;
    TextView likesNumberView;
    TextView repostsNumberView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_view, container, false);

        userImgView = (ImageView) view.findViewById(R.id.large_user_img_view);
        backImage = (ImageView) view.findViewById(R.id.back_img_button);
        likeImage = (ImageView) view.findViewById(R.id.like_photo);
        repostImage = (ImageView) view.findViewById(R.id.repost_photo);
        likesNumberView = (TextView) view.findViewById(R.id.likes_photo_count);
        repostsNumberView = (TextView) view.findViewById(R.id.reposts_photo_count);


        Bundle bundle = getArguments();
        photoId = bundle.getString("photo_id");

        new TaskRunner().executeAsync(new ImageLoadTask(photoId), (image) -> {
            userImgView.setImageBitmap(image);
        });

        try {
            likesNumberView.setText(bundle.getString("likes"));
            repostsNumberView.setText(bundle.getString("reposts"));
        } catch (Exception e) {

        }


        //Возврат назад
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}