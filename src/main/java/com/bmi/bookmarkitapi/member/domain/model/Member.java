package com.bmi.bookmarkitapi.member.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.NORMAL;

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public void modifyInfo(String email, String name, String password) {
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
        if (password != null && !password.isBlank()) {
            this.password = password;
        }
    }

    public void modifyRole(MemberRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role.getAuthority());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
