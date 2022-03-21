package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkRepository;
import com.bmi.bookmarkitapi.common.util.DateTimeUtils;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkCategoryQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkRecentQueryResponse;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@Transactional
@SpringBootTest
class MemberBookmarkListQueryServiceTest {

    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    MemberBookmarkService memberBookmarkService;
    @Autowired
    MemberBookmarkListQueryService memberBookmarkListQueryService;

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
        Bookmark bookmark2 = new Bookmark(link2);
        bookmark2.setCategory(mainCategory1, "DS");
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

    @DisplayName("회원이 특정 카테고리의 북마크를 요청하면 해당 카테고리에 해당하는 북마크들을 서브 카테고리로 묶어 반환한다")
    @Test
    void getBookmarksByCategory() {
        Long memberId = 1L;
        String category = "CS";
        MemberBookmarkCategoryQueryRequest request = new MemberBookmarkCategoryQueryRequest(memberId, category);

        List<MemberBookmarkCategoryQueryResponse> result = memberBookmarkListQueryService.getBookmarksByCategory(request);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getMainCategory()).isEqualTo(category);
        assertThat(result.get(0).getSubCategories().size()).isEqualTo(2);

        AbstractListAssert<?, List<?>, Object, ObjectAssert<Object>> MemberBookmarks = assertThat(result.get(0).getSubCategories())
                .filteredOn(subCategory -> subCategory.getSubCategory().equals("Algorithm"))
                .flatExtracting("memberBookmarks");
        MemberBookmarks.extracting("title").containsExactly("알고리즘"); // 리스트 형태이기 때문에 isEqual 사용 불가
        MemberBookmarks.extracting("link").containsExactly("https://algorithm.com");

        MemberBookmarks = assertThat(result.get(0).getSubCategories())
                .filteredOn(subCategory -> subCategory.getSubCategory().equals("DS"))
                .flatExtracting("memberBookmarks");
        MemberBookmarks.extracting("title").containsExactly("자료구조");
        MemberBookmarks.extracting("link").containsExactly("https://data-structure.com");
    }

    @DisplayName("회원이 최근에 등록한 북마크를 요청하면 날짜별로 북마크를 묶은 뒤 최신순으로 반환한다")
    @Test
    void getRecentBookmarks() {
        Long memberId = 1L;
        String now = DateTimeUtils.toStringWithMonthAndDay(LocalDateTime.now());
        MemberBookmarkRecentQueryRequest request = new MemberBookmarkRecentQueryRequest(memberId);

        List<MemberBookmarkRecentQueryResponse> result = memberBookmarkListQueryService.getRecentBookmarks(request);

        assertThat(result.size()).isEqualTo(1);

        MemberBookmarkRecentQueryResponse response = result.get(0);
        assertThat(response.getCreatedDate()).isEqualTo(now);
        assertThat(response.getMemberBookmarks().size()).isEqualTo(3);

        List<MemberBookmarkRecentQueryResponse.MemberBookmarkDto> memberBookmarks = response.getMemberBookmarks();
        assertThat(memberBookmarks).extracting("title").containsExactly("자바", "자료구조", "알고리즘");
        assertThat(memberBookmarks).extracting("subCategory").containsExactly("Java", "DS", "Algorithm");
    }
}
