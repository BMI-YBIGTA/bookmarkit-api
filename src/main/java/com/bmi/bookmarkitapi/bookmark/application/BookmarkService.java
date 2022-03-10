package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.BookmarkDto;
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
public class BookmarkService {

    private final BookmarkQueryService bookmarkQueryService;

    public BookmarkDto.Response findById(Long id) {
        return new BookmarkDto.Response(bookmarkQueryService.findById(id));
    }

    public List<BookmarkDto.Response> findAll() {
        return bookmarkQueryService.findAll()
                .stream()
                .map(BookmarkDto.Response::new)
                .collect(Collectors.toList());
    }

    public Page<BookmarkDto.Response> findByPage(int page, int size) {
        return bookmarkQueryService.findByPage(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")))
                .map(BookmarkDto.Response::new);
    }
}
