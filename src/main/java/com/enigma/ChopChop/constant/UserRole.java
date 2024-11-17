package com.enigma.ChopChop.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private final String Description;

    UserRole(String description) {
        this.Description = description;
    }

    public static UserRole findByDescription(String description) {
        for (UserRole role : values()) {
            if (role.Description.equalsIgnoreCase(description)) {
                return role;
            }
        }
        return null;
    }
}
