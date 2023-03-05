package com.example.theonaui2.ui.main.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.example.theonaui2.R;
import com.github.javafaker.Faker;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.example.theonaui2.ui.MainActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ChatElementData {
    private String chatId;
    private String chatName;
    private Bitmap chatImage;
    private ArrayList<Message> cachedMessages;
    private int unreadMessagesCount;

    private String lastMessageText;
    private long lastMessageTimestamp;
    private String lastMessageSender;

    public ChatElementData(String chatName, Bitmap chatImage, ArrayList<Message> cachedMessages, int unreadMessagesCount) {
        this.chatId = UUID.randomUUID().toString();
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.cachedMessages = cachedMessages;
        this.unreadMessagesCount = unreadMessagesCount;
        if (cachedMessages.get(cachedMessages.size() - 1).getMessageImage() != null) {
            lastMessageText = "📷Photo";
        } else {
            lastMessageText = cachedMessages.get(cachedMessages.size() - 1).getMessageText();
        }
        this.lastMessageTimestamp = cachedMessages.get(cachedMessages.size() - 1).getTimestamp();
        this.lastMessageSender = cachedMessages.get(cachedMessages.size() - 1).getMessageSender();
    }

    public ChatElementData( ) {}
    public String getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public Bitmap getChatImage() {
        return chatImage;
    }

    public ArrayList<Message> getCachedMessages() {
        return cachedMessages;
    }

    public int getUnreadMessagesCount() {
        return unreadMessagesCount;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public long getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public String getLastMessageSender() {
        return lastMessageSender;
    }


    public ChatElementData createTestData() {
        Faker faker = new Faker();
        ArrayList<Message> cachedMessages = new ArrayList<>();
        Message msg = new Message(UUID.randomUUID().toString(),
                faker.date().past(365, TimeUnit.DAYS).getTime(),
                faker.lorem().sentence(5),
                faker.name().fullName());
        cachedMessages.add(msg);

        String chatName = faker.name().fullName();
        int unreadMessagesCount = new Random().nextInt(15);

        //get empty rounded bitmap
        Bitmap chatImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        return new ChatElementData(chatName, chatImage, cachedMessages, unreadMessagesCount);
    }

    private static Bitmap downloadImageFromPath(Faker faker) {
        final Bitmap[] chatImage = {null};
        Picasso.get().load(faker.avatar().image()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                chatImage[0] = bitmap;
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        });

        return chatImage[0];
    }
}
