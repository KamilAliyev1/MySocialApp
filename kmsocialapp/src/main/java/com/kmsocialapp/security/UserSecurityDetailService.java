package com.kmsocialapp.security;

import com.kmsocialapp.myutil.CustomService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class UserSecurityDetailService extends CustomService<UserSecurityDetail> implements UserDetailsService {

    public UserSecurityDetailService(UserSecurityDetailDao userSecurityDetailDao, PasswordEncoder passwordEncoder) {
        super(userSecurityDetailDao);
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserSecurityDetail changeForRest(UserSecurityDetail userSecurityDetail) {
        userSecurityDetail.setUserProfile(null);
        userSecurityDetail.setAuthorities(null);
        return userSecurityDetail;
    }

    @Override
    public void save(UserSecurityDetail userSecurityDetail) {
        userSecurityDetail.setPassword(passwordEncoder.encode(userSecurityDetail.getPassword()));
        jpaRepository.save(userSecurityDetail);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityDetailDao userSecurityDetailDao = (UserSecurityDetailDao) jpaRepository;
        return userSecurityDetailDao.findByEmail(username)
                .or(new Supplier<Optional<? extends UserSecurityDetail>>() {
                    @Override
                    public Optional<? extends UserSecurityDetail> get() {
                        return userSecurityDetailDao.findByPhonenumber(username);
                    }
                })
                .orElseThrow(()->new IllegalStateException(String.format("Email or Phonenumber %s not founded",username)));
    }
}
