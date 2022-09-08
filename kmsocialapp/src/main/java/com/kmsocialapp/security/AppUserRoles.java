package com.kmsocialapp.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kmsocialapp.security.AppUserPermission.*;


public enum AppUserRoles {
    USER(new HashSet<>()),
    ADMIN(Set.of(USER_GET,USER_PUT,USER_DELETE,USER_POST));

    private final Set<AppUserPermission> permissions;

    public Set<SimpleGrantedAuthority> getAuth(){
        var l =  permissions.stream().map(permissions -> new SimpleGrantedAuthority(permissions.getPermission())).collect(Collectors.toSet());
        l.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return l;
    }

    AppUserRoles(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }
}
