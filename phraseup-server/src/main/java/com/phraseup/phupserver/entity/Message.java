package com.phraseup.phupserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    @Column(name = "text")
    private String text;

    @Column(name = "active_for")
    private int activeFor;

    public Message() {
    }

    public Message(int id, int senderId, Timestamp sentAt, String text) {
        this.id = id;
        this.senderId = senderId;
        this.sentAt = sentAt;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getActiveFor() {
        return activeFor;
    }

    public void setActiveFor(int activeFor) {
        this.activeFor = activeFor;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", sentAt=" + sentAt +
                ", text='" + text + '\'' +
                ", activeFor=" + activeFor +
                '}';
    }
}
