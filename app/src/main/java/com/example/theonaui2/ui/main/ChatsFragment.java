package com.example.theonaui2.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theonaui2.AddvialinkActivity;
import com.example.theonaui2.QRScanActivity;
import com.example.theonaui2.R;
import com.example.theonaui2.ui.main.chat.ChatActivity;
import com.example.theonaui2.ui.main.data.ChatElementData;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class ChatsFragment extends Fragment {
    private FloatingActionButton addvialink;
    private FloatingActionButton addviaqr;

    private ArrayList<ChatElementData> chats;


    RecyclerView chatsList;

    View view;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = getLayoutInflater().inflate(R.layout.fragment_chats, null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chats, container, false);
        chatsList = view.findViewById(R.id.chats_list);

        chats = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            chats.add(new ChatElementData().createTestData(getContext()));
        }

        //sort by last message timestamp(from newest to oldest)

        new Thread (new Runnable() {
            @Override
            public void run() {
                Collections.sort(chats, new Comparator<ChatElementData>() {
                    @Override
                    public int compare(ChatElementData lhs, ChatElementData rhs) {
                        return Long.compare(rhs.getLastMessageTimestamp(), lhs.getLastMessageTimestamp());
                    }
                });
            }
        });

        ChatListAdapter adapter = new ChatListAdapter(getActivity(), chats);
        chatsList.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        chatsList.setLayoutManager(new LinearLayoutManager(getActivity()));


        ItemClickSupport.addTo(chatsList).setOnItemClickListener(
                (recyclerView, position, v) -> {
                    ChatElementData chat = chats.get(position);
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    intent.putExtra("chat", chat.getChatId());
                    startActivity(intent);
                });

        bindView();

        addvialink.setOnClickListener(view -> {
            System.out.println("1st clicked");
            Intent intent = new Intent(getActivity(), AddvialinkActivity.class);
            startActivity(intent);
        });

        addviaqr.setOnClickListener(view -> {
            System.out.println("2nd clicked");
            Intent intent = new Intent(getActivity(), QRScanActivity.class);
            intent.putExtra("uuid", UUID.randomUUID().toString());
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void bindView() {
        addvialink = view.findViewById(R.id.addvialink1);
        addviaqr = view.findViewById(R.id.addviaqr1);
    }

}
