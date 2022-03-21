package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberBookmarkQueryService extends BaseQueryService<MemberBookmark> {

    private final MemberBookmarkRepository memberBookmarkRepository;

    public MemberBookmarkQueryService(MemberBookmarkRepository memberBookmarkRepository) {
        super(memberBookmarkRepository);
        this.memberBookmarkRepository = memberBookmarkRepository;
    }

    public List<MemberBookmark> findByMemberId(Long memberId) {
        return memberBookmarkRepository.findByMemberId(memberId);
    }

    public List<MemberBookmark> search(Long memberId, String searchText) {
        return memberBookmarkRepository.search(memberId, searchText);
    }
}
