package com.example.theonaui2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.theonaui2.R;
import com.example.theonaui2.ui.MainActivity;
import com.example.theonaui2.ui.main.chat.ChatActivity;

public class ChatsFragment extends Fragment {
    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button goToChatButton = (Button) getView().findViewById(R.id.goToChatButton);
        goToChatButton.setOnClickListener(v -> {
            //Go to chat/ChatActivity.kt with intent
            startActivity(new Intent(getActivity(), ChatActivity.class));
        });
    }


}
