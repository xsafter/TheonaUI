package com.example.theonaui2.ui.main.data;

import android.graphics.Bitmap;

public class Message {
    private String messageUUID;
    private long timestamp;
    private String messageText;
    private Bitmap messageImage;
    private String messageSender;

    public Message(String messageUUID, long timestamp, String messageText, String messageSender) {
        this.messageUUID = messageUUID;
        this.timestamp = timestamp;
        this.messageText = messageText;
        this.messageSender = messageSender;
    }

    public Message(String messageUUID, long timestamp, String messageText, Bitmap messageImage, String messageSender) {
        this.messageUUID = messageUUID;
        this.timestamp = timestamp;
        this.messageText = messageText;
        this.messageImage = messageImage;
        this.messageSender = messageSender;
    }

    public String getMessageUUID() {
        return messageUUID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessageText() {
        return messageText;
    }

    public Bitmap getMessageImage() {
        return messageImage;
    }

    public String getMessageSender() {
        return messageSender;
    }
}
