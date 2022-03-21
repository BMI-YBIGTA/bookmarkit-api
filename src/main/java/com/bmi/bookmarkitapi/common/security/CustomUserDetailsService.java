package com.bmi.bookmarkitapi.common.security;

import com.bmi.bookmarkitapi.member.domain.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberQueryService memberQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberQueryService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다. email=" + username));
    }
}
