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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theonaui2.AddvialinkActivity;
import com.example.theonaui2.QRScanActivity;
import com.example.theonaui2.R;
import com.example.theonaui2.ui.MainActivity;
import com.example.theonaui2.ui.main.chat.ChatActivity;
import com.example.theonaui2.ui.main.data.ChatElementData;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

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
        bindView();

        chatsList = view.findViewById(R.id.chats_list);

        chats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chats.add(new ChatElementData().createTestData());
        }

        RecyclerView.Adapter<ChatListAdapter.ViewHolder> adapter = new ChatListAdapter(getActivity(), chats);
        chatsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        chatsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chats, container, false);
        chatsList = view.findViewById(R.id.chats_list);
        return view;
    }

    private void bindView() {
        addvialink = view.findViewById(R.id.addvialink1);
        addviaqr = view.findViewById(R.id.addviaqr1);
    }
}
