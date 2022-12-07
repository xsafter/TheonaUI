package com.example.theonaui2.ui.main.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.theonaui2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestActivity extends AppCompatActivity {

    private Boolean eachFabVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        FloatingActionButton baseFab = findViewById(R.id.base_add_fab);
        FloatingActionButton callFab = findViewById(R.id.qr_fab);
        FloatingActionButton videoFab = findViewById(R.id.link_fab);
        TextView textOne = findViewById(R.id.text_one);
        TextView textTwo = findViewById(R.id.text_two);

        videoFab.setVisibility(View.GONE);
        callFab.setVisibility(View.GONE);
        textOne.setVisibility(View.GONE);
        textTwo.setVisibility(View.GONE);

        eachFabVisible = false;

        baseFab.setOnClickListener(v -> {
            if (!eachFabVisible) {
                eachFabVisible = true;
                videoFab.show();
                callFab.show();
                textOne.setVisibility(View.VISIBLE);
                textTwo.setVisibility(View.VISIBLE);
            } else {
                videoFab.hide();
                callFab.hide();
                textOne.setVisibility(View.GONE);
                textTwo.setVisibility(View.GONE);
                eachFabVisible = false;
            }
        });
    }
}