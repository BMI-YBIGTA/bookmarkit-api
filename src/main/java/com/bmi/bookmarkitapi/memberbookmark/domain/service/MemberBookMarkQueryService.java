package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberBookMarkQueryService extends BaseQueryService<MemberBookMark> {
    private final MemberBookMarkRepository memberBookMarkRepository;

    public MemberBookMarkQueryService(MemberBookMarkRepository repository) {
        super(repository, new MemberBookMarkNotFoundException());
        this.memberBookMarkRepository = repository;
    }

    public List<MemberBookMark> queryByMember(Long memberId){
        return memberBookMarkRepository.findByMemberId(memberId);
    }

    public List<MemberBookMark> queryByTitleContains(Long memberId,String searchText){
        return memberBookMarkRepository.findByMemberIdEqualsAndTitleContains(memberId,searchText);
    }


}
