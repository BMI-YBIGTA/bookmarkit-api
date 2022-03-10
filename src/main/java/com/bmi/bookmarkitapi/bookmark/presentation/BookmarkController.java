package com.bmi.bookmarkitapi.bookmark.presentation;

import com.bmi.bookmarkitapi.bookmark.application.BookmarkService;
import com.bmi.bookmarkitapi.bookmark.application.model.BookmarkDto;
import com.bmi.bookmarkitapi.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public Response.ItemList<BookmarkDto.Response> findAll() {
        return new Response.ItemList<>(bookmarkService.findAll());
    }

    @GetMapping("/{id}")
    public Response.Item<BookmarkDto.Response> findById(@PathVariable Long id) {
        return new Response.Item<>(bookmarkService.findById(id));
    }

    @GetMapping("/page")
    public Response.ItemPage<BookmarkDto.Response> findByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "30") int size
    ) {
        return new Response.ItemPage<>(bookmarkService.findByPage(page, size));
    }
}
