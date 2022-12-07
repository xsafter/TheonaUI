package com.example.theonaui2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.theonaui2.R;
import com.example.theonaui2.ui.MainActivity;
import com.example.theonaui2.ui.main.chat.ChatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatsFragment extends Fragment {
    private Boolean eachFabVisible;
    View view;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.fragment_chats, null);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chats, container, false);
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    public void openQRActivity(View view) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }

    public void openLinkActivity(View view) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }

    public void openChatActivity(View view) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }
}
