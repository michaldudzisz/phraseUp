package com.phraseup.phupserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "language")
    private String language;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private int userId;

    @OneToMany(mappedBy = "chatId")
    private List<Message> messages;

    public Chat() {

    }

    public Chat(int id, String language, int userId) {
        this.id = id;
        this.language = language;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", userId=" + userId +
                ", messages=" + messages +
                '}';
    }
}
