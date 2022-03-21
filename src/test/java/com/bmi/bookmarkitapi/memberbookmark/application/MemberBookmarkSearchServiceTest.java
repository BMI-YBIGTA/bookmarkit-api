package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkRepository;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@Transactional
@SpringBootTest
class MemberBookmarkSearchServiceTest {

    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    MemberBookmarkService memberBookmarkService;
    @Autowired
    MemberBookmarkSearchService memberBookmarkSearchService;

    @BeforeEach
    void setUp() {
        Long memberId = 1L;
        String mainCategory1 = "CS";
        String mainCategory2 = "Language";
        String link1 = "https://algorithm.com";
        String link2 = "https://data-structure.com";
        String link3 = "https://java.com";

        Bookmark bookmark1 = new Bookmark(link1);
        bookmark1.setCategory(mainCategory1, "Algorithm");
        bookmark1.setContent("알고리즘 자료입니다.");
        Bookmark bookmark2 = new Bookmark(link2);
        bookmark2.setCategory(mainCategory1, "DS");
        bookmark2.setContent("리스트에 대해 알아봅시다.");
        Bookmark bookmark3 = new Bookmark(link3);
        bookmark3.setCategory(mainCategory2, "Java");
        bookmarkRepository.saveAll(Arrays.asList(bookmark1, bookmark2, bookmark3));

        MemberBookmarkRegisterRequest request1 = new MemberBookmarkRegisterRequest();
        request1.setTitle("알고리즘");
        request1.setLink(link1);
        memberBookmarkService.register(memberId, request1);

        MemberBookmarkRegisterRequest request2 = new MemberBookmarkRegisterRequest();
        request2.setTitle("자료구조");
        request2.setLink(link2);
        memberBookmarkService.register(memberId, request2);

        MemberBookmarkRegisterRequest request3 = new MemberBookmarkRegisterRequest();
        request3.setTitle("자바");
        request3.setLink(link3);
        memberBookmarkService.register(memberId, request3);
    }

    @DisplayName("회원이 북마크를 검색하면 카테고리, 제목, 본문에 검색어가 포함된 북마크를 반환한다")
    @Test
    void search() {
        Long memberId = 1L;
        String searchText = "자료";
        MemberBookmarkSearchRequest request = new MemberBookmarkSearchRequest(memberId, null, searchText, 5);

        List<MemberBookmarkSearchResponse> result = memberBookmarkSearchService.search(request);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).extracting("mainCategory").containsOnly("CS");
        assertThat(result).extracting("subCategory").containsExactlyInAnyOrder("Algorithm", "DS");
        assertThat(result).extracting("title").containsExactlyInAnyOrder("알고리즘", "자료구조");
        assertThat(result).extracting("link").containsExactlyInAnyOrder("https://algorithm.com", "https://data-structure.com");
        assertThat(result).extracting("content").containsExactlyInAnyOrder("자료입니다.", "리스트에 대해 알아봅시다.");
    }
}
