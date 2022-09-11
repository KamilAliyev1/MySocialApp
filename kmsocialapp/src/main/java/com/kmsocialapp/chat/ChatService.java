package com.kmsocialapp.chat;


import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourceOwnerException;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements CustomService<Chat> {

    private final ChatDao chatDao;

    public ChatService(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public Chat findById(Long id) {
        checkPermisson(id);
        var optional = chatDao.findById(id);
        return optional.get();
    }

    @Override
    public List findAll() {
        return chatDao.findAll();
    }

    @Override
    public void save(Chat obj) {
        chatDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return chatDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        chatDao.deleteById(id);
    }

    @Override
    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var optional = chatDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        if(optional.get().getUserProfiles().stream().anyMatch(u->u.getId()==userSecurityDetail.getId()))
            return;
        throw new ResourceOwnerException();
    }
}
