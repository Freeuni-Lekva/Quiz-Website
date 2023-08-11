package models;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private String fromUsername;
    private String toUsername;
    private String message;
    private Timestamp sentDate;

    public Message(int messageId, String fromUsername, String toUsername, String message, Timestamp sentDate) {
        this.messageId = messageId;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.message = message;
        this.sentDate = sentDate;
    }

    public Message(String fromUsername, String toUsername, String message, Timestamp sentDate) {
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.message = message;
        this.sentDate = sentDate;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return this.message;
    }

    public Timestamp getSentDate() {
        return this.sentDate;
    }

    public String getFromUsername() {
        return this.fromUsername;
    }

    public String getToUsername() {
        return this.toUsername;
    }

    public int getMessageId() {
        return this.messageId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", fromUsername='" + fromUsername + '\'' +
                ", toUsername='" + toUsername + '\'' +
                ", message='" + message + '\'' +
                ", sentDate=" + sentDate +
                '}';
    }
}
