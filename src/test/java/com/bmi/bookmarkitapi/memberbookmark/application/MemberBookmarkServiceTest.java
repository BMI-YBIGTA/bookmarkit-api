package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.infrastructure.BookmarkRegistrationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberBookmarkServiceTest {

    @Mock
    MemberBookmarkQueryService memberBookmarkQueryService;
    @Mock
    MemberBookmarkCommandService memberBookmarkCommandService;
    @Mock
    BookmarkRegistrationService bookmarkRegistrationService;
    @InjectMocks
    MemberBookmarkServiceImpl memberBookmarkService;

    @DisplayName("회원이 북마크를 등록하면 bookmark를 저장한 뒤 memberBookmark를 등록한다")
    @Test
    void register() {
        Long memberId = 1L;
        Long bookmarkId = 1L;
        String title = "스프링";
        String link = "https://blog.spring.com";
        MemberBookmarkRegisterRequest request = new MemberBookmarkRegisterRequest();
        request.setLink(link);
        request.setTitle(title);

        when(bookmarkRegistrationService.saveIfNotExist(link)).thenReturn(new Bookmark(link));
        when(memberBookmarkCommandService.create(memberId, null, title)).thenReturn(new MemberBookmark(memberId, bookmarkId, title));

        MemberBookmarkResponse result = memberBookmarkService.register(memberId, request);

        assertThat(result.getBookmarkId()).isEqualTo(bookmarkId);
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getTitle()).isEqualTo(title);
        verify(bookmarkRegistrationService).saveIfNotExist(link);
        verify(memberBookmarkCommandService).create(memberId, null, title);
    }

    @DisplayName("회원이 북마크의 수정할 제목을 요청으로 보내면 북마크의 제목이 수정된다")
    @Test
    void modifyTitle() {
        Long id = 1L;
        Long memberId = 1L;
        Long bookmarkId = 1L;
        String title = "스프링";
        String modifiedTitle = "스프링부트";
        MemberBookmarkTitleModifyRequest request = new MemberBookmarkTitleModifyRequest();
        request.setTitle(modifiedTitle);

        when(memberBookmarkQueryService.findById(id)).thenReturn(new MemberBookmark(memberId, bookmarkId, title));

        MemberBookmarkResponse result = memberBookmarkService.modifyTitle(id, request);

        assertThat(result.getBookmarkId()).isEqualTo(bookmarkId);
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getTitle()).isEqualTo(modifiedTitle);
        verify(memberBookmarkQueryService).findById(id);
    }
}
