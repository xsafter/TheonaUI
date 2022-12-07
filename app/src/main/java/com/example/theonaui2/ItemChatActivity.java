package com.example.theonaui2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.theonaui2.ui.main.chat.ChatActivity;

public class ItemChatActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chat);
        //If the user clicks on the item, go to the chat activity

    }

    @Override
    public void onClick(View v) {
        //Go to chat/ChatActivity.kt with intent
        startActivity(new Intent(ItemChatActivity.this, ChatActivity.class));
    }

    public void clickItem(View view) {
        //Go to chat/ChatActivity.kt with intent
        startActivity(new Intent(ItemChatActivity.this, ChatActivity.class));
    }
}