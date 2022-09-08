package com.kmsocialapp.message;

import com.kmsocialapp.myutil.CustomRestController;
import com.kmsocialapp.myutil.CustomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageRestController extends CustomRestController<Message> {
    public MessageRestController(MessageService service) {
        super(service);
    }
}
