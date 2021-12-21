package com.bmi.bookmarkitapi.member.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberCommandService extends BaseCommandService<Member> {

    public MemberCommandService(MemberRepository memberRepository) {
        super(memberRepository);
    }
}
