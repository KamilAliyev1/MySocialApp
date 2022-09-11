package com.kmsocialapp.userprofile;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourceOwnerException;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserProfileService implements CustomService<UserProfile> {

    private final UserProfileDao userProfileDao;

    private final UserSecurityDetailService userSecurityDetailService;

    public UserProfileService(UserProfileDao userProfileDao, UserSecurityDetailService userSecurityDetailService) {
        this.userProfileDao = userProfileDao;
        this.userSecurityDetailService = userSecurityDetailService;
    }

    @Override
    public UserProfile findById(Long id) {
        checkPermisson(id);
        var optional = userProfileDao.findById(id);
        return optional.get();
    }

    @Override
    public List findAll() {
        return userProfileDao.findAll();
    }

    @Override
    public void save(UserProfile obj) {
        userSecurityDetailService.checkPermisson(obj.getUserSecurityDetail().getId());
        userProfileDao.save(obj);
    }

    @Override
    public boolean existsById(Long id) {
        return userProfileDao.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        userProfileDao.deleteById(id);
    }
    @Override
    public void checkPermisson(Long id){
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userSecurityDetail.getUserProfile().getId()==id)return;
        var optional = userProfileDao.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        if(optional.get().getProfileType().equals(ProfileType.PUBLIC))return;
        throw new ResourceOwnerException();
    }
}
