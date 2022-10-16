package com.book.store.Security;

public enum ApplicationUserPermission {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    AUTHOR_READ("author:read"),
    AUTHOR_WRITE("author:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}