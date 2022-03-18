package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.application.model.response.BookmarkResponse;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkCommandService bookmarkCommandService;
    private final BookmarkQueryService bookmarkQueryService;

    @Override
    public List<BookmarkResponse> findAll() {
        return bookmarkQueryService.findAll()
                .stream()
                .map(BookmarkResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public BookmarkResponse findById(Long id) {
        return new BookmarkResponse(bookmarkQueryService.findById(id));
    }

    @Override
    public Page<BookmarkResponse> findByPage(int page, int size) {
        return bookmarkQueryService.findByPage(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")))
                .map(BookmarkResponse::new);
    }

    @Override
    public Bookmark register(BookmarkRegisterRequest request) {
        return bookmarkCommandService.saveWithLink(request.getLink());
    }
}
