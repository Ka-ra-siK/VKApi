package ru.eltex.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.eltex.R;
import ru.eltex.api_service.WebClient;

/**
 * Фрагмент отображающий ссылки в собсвтенном web линете
 */
public class LinkFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link, container, false);
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            String url = bundle.getString("url");
            WebView webView = view.findViewById(R.id.web_view);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
            webView.setWebViewClient(new WebClient(view.getContext()));
        }
        return view;
    }

}
