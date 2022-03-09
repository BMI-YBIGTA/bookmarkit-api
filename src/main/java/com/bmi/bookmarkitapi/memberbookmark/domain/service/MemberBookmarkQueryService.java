package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberBookmarkQueryService extends BaseQueryService<MemberBookmark> {
    private final MemberBookmarkRepository memberBookmarkRepository;

    public MemberBookmarkQueryService(MemberBookmarkRepository repository) {
        super(repository, new ResourceNotFoundException());
        this.memberBookmarkRepository = repository;
    }

    public List<MemberBookmark> queryByMember(Long memberId){
        return memberBookmarkRepository.findByMemberId(memberId);
    }

    public List<MemberBookmark> queryByTitleContains(Long memberId, String searchText){
        return memberBookmarkRepository.findByMemberIdEqualsAndTitleContains(memberId, searchText);
    }
}
