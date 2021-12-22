package com.bmi.bookmarkitapi.member.domain.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum MemberRole {
    NORMAL(new SimpleGrantedAuthority("normal")),
    ADMIN(new SimpleGrantedAuthority("admin"));

    private final GrantedAuthority authority;

    MemberRole(GrantedAuthority authority) {
        this.authority = authority;
    }
}
