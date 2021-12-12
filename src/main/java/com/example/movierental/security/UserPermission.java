package com.example.movierental.security;

public enum UserPermission {
    USER_RENT("user:rent"),
    ADMIN_BAN("admin:ban"), ADMIN_ADD("admin:add"), ADMIN_DELETE("admin:delete");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
