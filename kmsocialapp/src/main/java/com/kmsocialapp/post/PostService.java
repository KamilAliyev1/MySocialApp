package com.kmsocialapp.post;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourceOwnerException;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.userprofile.ProfileType;
import com.kmsocialapp.userprofile.UserProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements CustomService<Post> {

    private final PostDao postDao;

    private final UserProfileService userProfileService;

    public PostService(PostDao postDao, UserProfileService userProfileService) {
        this.postDao = postDao;
        this.userProfileService = userProfileService;
    }


    @Override
    public Post findById(Long id) {
        checkPermisson(id);
        var optional = postDao.findById(id);
        return optional.get();
    }

    @Override
    public List findAll() {
        return postDao.findAll();
    }

    @Override
    public void save(Post obj) {
        userProfileService.checkPermisson(obj.getUserProfile().getId());
        postDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return postDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        postDao.deleteById(id);
    }

    @Override
    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userSecurityDetail.getUserProfile().getPosts().stream().anyMatch(p->p.getId()==id))return;
        var optional = postDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        if(optional.get().getUserProfile().getProfileType().equals(ProfileType.PUBLIC))return;
        throw new ResourceOwnerException();

    }
}
