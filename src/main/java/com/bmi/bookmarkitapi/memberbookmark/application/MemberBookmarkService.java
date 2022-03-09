package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkDto;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberBookmarkService {

    private final MemberBookmarkQueryService memberBookmarkQueryService;

    public MemberBookmarkDto.Response findById(Long id) {
        return new MemberBookmarkDto.Response(memberBookmarkQueryService.query(id));
    }

    public List<MemberBookmarkDto.Response> findAll() {
        return memberBookmarkQueryService.query()
                .stream()
                .map(MemberBookmarkDto.Response::new)
                .collect(Collectors.toList());
    }

    public Page<MemberBookmarkDto.Response> findByPage(int page, int size) {
        return memberBookmarkQueryService.query(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")))
                .map(MemberBookmarkDto.Response::new);
    }
}
