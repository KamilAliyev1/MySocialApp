package com.kmsocialapp.security.usersecuritydetail;

import com.kmsocialapp.myutil.CustomService;
import com.kmsocialapp.myutil.ResourceOwnerException;
import com.kmsocialapp.myutil.ResourcesNotFounded;
import com.kmsocialapp.userprofile.UserProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserSecurityDetailService implements UserDetailsService,CustomService<UserSecurityDetail> {

    private final UserSecurityDetailDao jpaRepository;

    private final PasswordEncoder passwordEncoder;

    public UserSecurityDetailService(UserSecurityDetailDao userSecurityDetailDao, PasswordEncoder passwordEncoder) {
        this.jpaRepository = userSecurityDetailDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSecurityDetail findById(Long id) {
        checkPermisson(id);
        var optional = jpaRepository.findById(id);
        if(optional.isEmpty())throw new ResourcesNotFounded();
        return optional.get();
    }

    @Override
    public List findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void save(UserSecurityDetail userSecurityDetail) {
        userSecurityDetail.setPassword(passwordEncoder.encode(userSecurityDetail.getPassword()));
        jpaRepository.save(userSecurityDetail);
    }

    @Override
    public boolean existsById(Long id) {
        checkPermisson(id);
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        checkPermisson(id);
        if(!jpaRepository.existsById(id))throw new ResourcesNotFounded();
        jpaRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityDetailDao userSecurityDetailDao = (UserSecurityDetailDao) jpaRepository;
        var detail = userSecurityDetailDao.findByEmail(username)
                .or(new Supplier<Optional<? extends UserSecurityDetail>>() {
                    @Override
                    public Optional<? extends UserSecurityDetail> get() {
                        return userSecurityDetailDao.findByPhonenumber(username);
                    }
                })
                .orElseThrow(()->new IllegalStateException(String.format("Email or Phonenumber %s not founded",username)));
        return detail;
    }


    public void checkPermisson(Long id) {
        UserSecurityDetail userSecurityDetail = (UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(id == userSecurityDetail.getId())return;

        throw new ResourceOwnerException();
    }


    public UserSecurityDetail customloadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityDetailDao userSecurityDetailDao = (UserSecurityDetailDao) jpaRepository;

        var detail = userSecurityDetailDao.findByEmail(username)
                .or(new Supplier<Optional<? extends UserSecurityDetail>>() {
                    @Override
                    public Optional<? extends UserSecurityDetail> get() {
                        return userSecurityDetailDao.findByPhonenumber(username);
                    }
                })
                .orElseThrow(()->new IllegalStateException(String.format("Email or Phonenumber %s not founded",username)));
        return detail;
    }
}
