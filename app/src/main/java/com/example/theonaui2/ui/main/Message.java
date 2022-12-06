package com.example.theonaui2.ui.main;

public class Message {
    private String message;
    private long time;

    private String sender;
    private String receiver;
    private boolean isMine;
    private boolean isRead;

    public Message(String message, long time, String sender, String receiver, boolean isMine, boolean isRead) {
        this.message = message;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
        this.isMine = isMine;
        this.isRead = isRead;
    }
}
