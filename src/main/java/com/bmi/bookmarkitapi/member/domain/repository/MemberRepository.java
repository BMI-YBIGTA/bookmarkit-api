package com.bmi.bookmarkitapi.member.domain.repository;

import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.member.domain.model.Member;

import java.util.Optional;

public interface MemberRepository extends BaseRepository<Member> {

    Optional<Member> findByEmail(String email);
}
