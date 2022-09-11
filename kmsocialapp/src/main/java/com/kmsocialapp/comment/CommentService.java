package com.kmsocialapp.comment;

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
public class CommentService implements CustomService<Comment> {

    private final CommentDao commentDao;

    private final PostService postService;

    private final UserProfileService userProfileService;

    public CommentService(CommentDao commentDao, PostService postService, UserProfileService userProfileService) {
        this.commentDao = commentDao;
        this.postService = postService;
        this.userProfileService = userProfileService;
    }

    @Override
    public Comment findById(Long id) {
        checkPermisson(id);
        return commentDao.findById(id).get();
    }

    @Override
    public List findAll() {
        return commentDao.findAll();
    }

    @Override
    public void save(Comment obj) {
        userProfileService.checkPermisson(obj.getUserProfile().getId());
        if(obj.getPost()!=null)postService.checkPermisson(obj.getPost().getId());
        if(obj.getParentComment()!=null)checkPermisson(obj.getParentComment().getId());
        commentDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return commentDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        commentDao.deleteById(id);
    }

    @Override
    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var optional = commentDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        var comment = optional.get();

        if(comment.getPost()!=null)postService.checkPermisson(comment.getPost().getId());

        if(comment.getParentComment()!=null)this.checkPermisson(comment.getParentComment().getId());

        throw new ResourceOwnerException();

    }
}
