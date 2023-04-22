package com.example.hosicare.modals;

public class Message {
    private String sender, receiver, context;

    public Message(){}
    public Message(String sender, String receiver, String context) {
        this.sender = sender;
        this.receiver = receiver;
        this.context = context;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
