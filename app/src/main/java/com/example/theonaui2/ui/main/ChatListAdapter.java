package com.example.theonaui2.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theonaui2.R;
import com.example.theonaui2.ui.main.data.ChatElementData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
        if (chatElementData.getUnreadMessagesCount() == 0) {
            holder.unreadMessagesCount.setVisibility(View.GONE);
        } else
            holder.unreadMessagesCount.setText(String.valueOf(chatElementData.getUnreadMessagesCount()));

        holder.lastMessageText.setText(chatElementData.getLastMessageText());
        //holder.lastMessageSender.setText(chatElementData.getLastMessageSender()); TODO: WTF?

        //make time in HH:MM format if it was sent in the last 24 hours
        //else, in MM::DD format
        long timeDifference = Math.abs(chatElementData.getLastMessageTimestamp() - Calendar.getInstance().getTimeInMillis());
        if (timeDifference < TimeUnit.DAYS.toMillis(2)) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = null;
            simpleDateFormat = new SimpleDateFormat("HH:mm");
            String formattedTimestamp = simpleDateFormat.format(chatElementData.getLastMessageTimestamp());

            holder.lastMessageTime.setText(formattedTimestamp);
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = null;
            simpleDateFormat = new SimpleDateFormat("MM.dd");
            String formattedTimestamp = simpleDateFormat.format(chatElementData.getLastMessageTimestamp());

            holder.lastMessageTime.setText(formattedTimestamp);
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

        View contactView = inflater.inflate(R.layout.item_chat, parent, false);

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
    }
}
