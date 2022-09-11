package com.kmsocialapp.message;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourceOwnerException;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import com.kmsocialapp.post.PostService;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
import com.kmsocialapp.userprofile.UserProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements CustomService<Message> {

    private final MessageDao messageDao;

    private final PostService postService;

    private final UserProfileService userProfileService;

    public MessageService(MessageDao messageDao, PostService postService, UserProfileService userProfileService) {
        this.messageDao = messageDao;
        this.postService = postService;
        this.userProfileService = userProfileService;
    }

    @Override
    public Message findById(Long id) {
        checkPermisson(id);
        var optional = messageDao.findById(id);
        return optional.get();
    }

    @Override
    public List findAll() {
        return messageDao.findAll();
    }

    @Override
    public void save(Message obj) {
        userProfileService.checkPermisson(obj.getUserProfile().getId());
        if(obj.getPost()!=null)postService.checkPermisson(obj.getPost().getId());
        messageDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return messageDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        messageDao.deleteById(id);
    }

    @Override
    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var optional = messageDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        if(userSecurityDetail.getUserProfile().getChats().stream().anyMatch(c->c.getMessages().stream().anyMatch(m->m.getId()==id)))
            return;

        throw new ResourceOwnerException();
    }
}
