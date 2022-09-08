package com.kmsocialapp.chat;

import com.kmsocialapp.myutil.CustomRestController;
import com.kmsocialapp.myutil.CustomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/chats")
public class ChatRestController extends CustomRestController<Chat> {
    public ChatRestController(ChatService service) {
        super(service);
    }
}
