package com.kmsocialapp.like;

import com.kmsocialapp.comment.CommentService;
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
public class LikeService implements CustomService<Like> {

    private final LikeDao likeDao;

    private final UserProfileService userProfileService;

    private final PostService postService;

    private final CommentService commentService;


    public LikeService(LikeDao likeDao, UserProfileService userProfileService, PostService postService, CommentService commentService) {
        this.likeDao = likeDao;
        this.userProfileService = userProfileService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public Like findById(Long id) {
        checkPermisson(id);
        var optional = likeDao.findById(id);
        return optional.get();
    }

    @Override
    public List findAll() {
        return likeDao.findAll();
    }

    @Override
    public void save(Like obj) {
        userProfileService.checkPermisson(obj.getUserProfile().getId());
        if(obj.getComment()!=null)commentService.checkPermisson(obj.getComment().getId());
        if(obj.getPost()!=null)postService.checkPermisson(obj.getPost().getId());
        likeDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return likeDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        likeDao.deleteById(id);
    }

    @Override
    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var optional = likeDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        var like = optional.get();
        if(like.getComment()!=null)commentService.checkPermisson(like.getComment().getId());
        if(like.getPost()!=null)postService.checkPermisson(like.getPost().getId());
        throw new ResourceOwnerException();

    }
}
