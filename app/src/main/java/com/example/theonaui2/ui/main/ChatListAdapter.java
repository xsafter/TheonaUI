package com.example.theonaui2.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theonaui2.R;
import com.example.theonaui2.ui.main.data.ChatElementData;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ChatElementData> chatElementDataArrayList;

    public ChatListAdapter(Context context, ArrayList<ChatElementData> chatElementDataArrayList) {
        this.context = context;
        this.chatElementDataArrayList = chatElementDataArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatElementData chatElementData = chatElementDataArrayList.get(position);
        holder.chatName.setText(chatElementData.getChatName());
        holder.chatImage.setImageBitmap(chatElementData.getChatImage());
        holder.unreadMessagesCount.setText(String.valueOf(chatElementData.getUnreadMessagesCount()));
        holder.lastMessageText.setText(chatElementData.getLastMessageText());
        holder.lastMessageSender.setText(chatElementData.getLastMessageSender());

        if (chatElementData.getLastMessageTimestamp() > 24 * 60 * 60 * 1000) {
            String day = String.valueOf(chatElementData.getLastMessageTimestamp() / (24 * 60 * 60 * 1000));
            String month = String.valueOf((chatElementData.getLastMessageTimestamp() / (24 * 60 * 60 * 1000)) % (24 * 60 * 60 * 1000));

            holder.lastMessageTime.setText(month + "." + day);
        } else {
            String hour = String.valueOf(chatElementData.getLastMessageTimestamp() / (60 * 60 * 1000));
            String minute = String.valueOf((chatElementData.getLastMessageTimestamp() / (60 * 60 * 1000)) % (60 * 60 * 1000));

            holder.lastMessageTime.setText(hour + ":" + minute);
        }
    }

    @Override
    public int getItemCount() {
        return chatElementDataArrayList.size();
    }

    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_chat, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView chatName;
        public ImageView chatImage;
        public TextView unreadMessagesCount;
        public TextView lastMessageText;
        public TextView lastMessageTime;
        public TextView lastMessageSender;
        public ViewHolder(View view) {
            super(view);
            chatName = view.findViewById(R.id.chatName);
            chatImage = view.findViewById(R.id.chatImage);
            lastMessageText = view.findViewById(R.id.lastMessageText);
            lastMessageTime = view.findViewById(R.id.lastMessageTime);
            //lastMessageSender = view.findViewById(R.id.lastMessageSender);
            unreadMessagesCount = view.findViewById(R.id.unreadMessagesCount);
        }

        public void bind(ChatElementData chatElementData) {
            chatName.setText(chatElementData.getChatName());
            chatImage.setImageBitmap(chatElementData.getChatImage());
            unreadMessagesCount.setText(String.valueOf(chatElementData.getUnreadMessagesCount()));
            lastMessageText.setText(chatElementData.getLastMessageText());
            //lastMessageSender.setText(chatElementData.getLastMessageSender());

            if (chatElementData.getLastMessageTimestamp() > 24 * 60 * 60 * 1000) {
                String day = String.valueOf(chatElementData.getLastMessageTimestamp() / (24 * 60 * 60 * 1000));
                String month = String.valueOf((chatElementData.getLastMessageTimestamp() / (24 * 60 * 60 * 1000)) % (24 * 60 * 60 * 1000));

                lastMessageTime.setText(month + "." + day);
            } else {
                String hour = String.valueOf(chatElementData.getLastMessageTimestamp() / (60 * 60 * 1000));
                String minute = String.valueOf((chatElementData.getLastMessageTimestamp() / (60 * 60 * 1000)) % (60 * 60 * 1000));

                lastMessageTime.setText(hour + ":" + minute);
            }
        }
    }
}
