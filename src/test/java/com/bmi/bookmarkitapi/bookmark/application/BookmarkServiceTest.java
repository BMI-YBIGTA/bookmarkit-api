package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkCommandService;
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
class BookmarkServiceTest {

    @Mock
    BookmarkCommandService bookmarkCommandService;
    @InjectMocks
    BookmarkServiceImpl bookmarkService;

    @DisplayName("링크로 북마크 등록 요청을 북마크가 저장되고 모델 서버로 전송되는데, 분류가 완료되지 않으면 링크만 포함하여 반환된다")
    @Test
    void register() {
        String link = "https://blog.com";
        Bookmark bookmark = new Bookmark(link);

        when(bookmarkCommandService.saveWithLink(link)).thenReturn(bookmark);

        Bookmark result = bookmarkService.register(new BookmarkRegisterRequest(link));

        assertThat(result.getLink()).isEqualTo(link);
        assertThat(result.getContent()).isEmpty();
        assertThat(result.getMainCategory()).isNull();
        assertThat(result.getSubCategory()).isNull();
        assertThat(result.getStatus()).isEqualTo(BookmarkStatus.INIT);
        verify(bookmarkCommandService).saveWithLink(link);
    }
}
