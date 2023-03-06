package com.example.theonaui2.ui.main.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.github.javafaker.Faker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

        Message msg = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            msg = new Message(UUID.randomUUID().toString(),
                    faker.date().past(7, TimeUnit.DAYS).getTime(),
                    faker.lorem().sentence(5),
                    faker.name().fullName());
        }
        cachedMessages.add(msg);

        String chatName = faker.name().fullName();
        int unreadMessagesCount = new Random().nextInt(10);

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

    @Override
    public String toString() {
        return "ChatElementData{" +
                "chatId='" + chatId + '\'' +
                ", chatName='" + chatName + '\'' +
                ", chatImage=" + chatImage +
                ", cachedMessages=" + cachedMessages +
                ", unreadMessagesCount=" + unreadMessagesCount +
                ", lastMessageText='" + lastMessageText + '\'' +
                ", lastMessageTimestamp=" + lastMessageTimestamp +
                ", lastMessageSender='" + lastMessageSender + '\'' +
                '}';
    }
}
