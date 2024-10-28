package com.Business_Intelligence.bi_system_backend.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    ANALYST("ROLE_ANALYST"),
    VIEWER("ROLE_VIEWER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}