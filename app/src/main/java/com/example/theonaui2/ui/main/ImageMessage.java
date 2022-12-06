package com.example.theonaui2.ui.main;

import android.graphics.Bitmap;

public class ImageMessage extends Message {
    private Bitmap image;

    public ImageMessage(String message, long time, String sender, String receiver, boolean isMine, boolean isRead) {
        super(message, time, sender, receiver, isMine, isRead);
    }
}
