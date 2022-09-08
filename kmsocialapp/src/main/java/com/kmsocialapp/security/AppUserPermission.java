package com.kmsocialapp.security;

public enum AppUserPermission {
    USER_GET("user:GET"),
    USER_POST("user:post"),
    USER_PUT("user:put"),
    USER_DELETE("user:delete");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
