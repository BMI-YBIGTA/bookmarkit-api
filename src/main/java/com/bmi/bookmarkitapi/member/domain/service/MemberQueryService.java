package com.bmi.bookmarkitapi.member.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberQueryService extends BaseQueryService<Member> {

    private final MemberRepository memberRepository;

    public MemberQueryService(MemberRepository memberRepository) {
        super(memberRepository, new ResourceNotFoundException());
        this.memberRepository = memberRepository;
    }

    public Optional<Member> query(String email) {
        return memberRepository.findByEmail(email);
    }
}
