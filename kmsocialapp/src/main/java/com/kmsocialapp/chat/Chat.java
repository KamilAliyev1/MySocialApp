package com.kmsocialapp.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmsocialapp.message.Message;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.userprofile.UserProfile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Chat implements Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "chats")
    private Set<UserProfile> userProfiles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;


    public Chat() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
