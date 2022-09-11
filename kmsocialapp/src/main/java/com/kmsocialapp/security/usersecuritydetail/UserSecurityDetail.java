package com.kmsocialapp.security.usersecuritydetail;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kmsocialapp.myutil.Resource;
import com.kmsocialapp.myutil.customconstraint.EitherOr;
import com.kmsocialapp.myutil.customconstraint.EitherorObject;
import com.kmsocialapp.userprofile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.beans.Transient;
import java.util.Collection;
import java.util.Set;

@EitherOr(message = "required email or password")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@Data
@Entity
public class UserSecurityDetail implements UserDetails, EitherorObject, Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phonenumber;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<GrantedAuthority> authorities;

    boolean isAccountNonExpired;
    boolean isAccountNonLocked ;
    boolean isCredentialsNonExpired ;
    boolean isEnabled ;

    public UserSecurityDetail() {
        isAccountNonExpired = true;
        isAccountNonLocked = true;
        isCredentialsNonExpired = true;
        isEnabled = true;
    }

    public UserSecurityDetail(String phonenumber, String email, String password,UserProfile userProfile) {
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.userProfile = userProfile;
        isAccountNonExpired = true;
        isAccountNonLocked = true;
        isCredentialsNonExpired = true;
        isEnabled = true;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email==null?phonenumber:email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Transient
    @Override
    public Object getFirstforEitheror() {
        return getUsername();
    }

    @Transient
    @Override
    public Object getSecondforEeitheror() {
        return getPhonenumber();
    }
}
