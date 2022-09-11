package com.kmsocialapp.mockrepo;


import com.kmsocialapp.security.AppUserRoles;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetail;
import com.kmsocialapp.security.usersecuritydetail.UserSecurityDetailDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository("fake")
public class MockUserSecurityDetail implements UserSecurityDetailDao {

    private final PasswordEncoder passwordEncoder;

    public MockUserSecurityDetail(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserSecurityDetail> getDetails(){
        return List.of(UserSecurityDetail.builder().email("k@mail.ru").password(passwordEncoder.encode("kamitto"))
                        .isAccountNonExpired(true)
                        .isEnabled(true)
                        .isCredentialsNonExpired(true)
                        .isAccountNonLocked(true)
                        .authorities(AppUserRoles.USER.getAuth().stream().map(e->(GrantedAuthority)e).collect(Collectors.toSet()))
                .build());
    }


    @Override
    public Optional<UserSecurityDetail> findByEmail(String email) {
        return getDetails().stream().findFirst();
    }

    @Override
    public Optional<UserSecurityDetail> findByPhonenumber(String phonenumber) {
        return getDetails().stream().findFirst().stream().findFirst();
    }

    @Override
    public List<UserSecurityDetail> findAll() {
        return null;
    }

    @Override
    public List<UserSecurityDetail> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<UserSecurityDetail> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserSecurityDetail> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserSecurityDetail> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserSecurityDetail getOne(Long aLong) {
        return null;
    }

    @Override
    public UserSecurityDetail getById(Long aLong) {
        return null;
    }

    @Override
    public UserSecurityDetail getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<UserSecurityDetail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> S save(S entity) {
        return null;
    }

    @Override
    public Optional<UserSecurityDetail> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserSecurityDetail entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserSecurityDetail> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserSecurityDetail> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserSecurityDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserSecurityDetail> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserSecurityDetail> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserSecurityDetail, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
