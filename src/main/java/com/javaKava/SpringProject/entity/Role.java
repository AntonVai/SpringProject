package com.javaKava.SpringProject.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    OWNER, MODERATOR, SULTAN, ALESHA, SUBSCRIBER, MEMBER, OLD;

    @Override
    public String getAuthority() {
        return name();
    }
}
