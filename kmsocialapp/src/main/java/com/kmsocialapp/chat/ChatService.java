package com.kmsocialapp.chat;


import com.kmsocialapp.myutil.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService extends CustomService<Chat> {
    public ChatService(ChatDao jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Chat changeForRest(Chat chat) {
        chat.setMessages(null);
        chat.setUserProfiles(null);
        return chat;
    }

    @Override
    public void save(Chat obj) {
        jpaRepository.save(obj);
    }
}
