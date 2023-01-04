package ru.eltex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ru.eltex.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.friend_button).setOnClickListener(v -> {
            Intent toWeb = new Intent(getApplicationContext(), FriendsActivity.class);
            startActivity(toWeb);
        });


    }
}