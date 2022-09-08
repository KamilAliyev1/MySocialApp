package com.kmsocialapp.message;

import com.kmsocialapp.myutil.CustomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends CustomService<Message> {
    public MessageService(MessageDao jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Message changeForRest(Message message) {

        return null;
    }

    @Override
    public void save(Message obj) {

    }
}
