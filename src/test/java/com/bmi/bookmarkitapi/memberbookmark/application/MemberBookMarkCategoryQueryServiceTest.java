package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkRepository;
import com.bmi.bookmarkitapi.member.domain.model.Member;
import com.bmi.bookmarkitapi.member.domain.repository.MemberRepository;
import com.bmi.bookmarkitapi.memberbookmark.application.model.*;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberBookMarkCategoryQueryServiceTest {

    @Autowired
    private MemberBookMarkCategoryQueryService categoryQueryService;
    @Autowired
    private  MemberBookMarkSearchService searchService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookMarkRepository bookMarkRepository;
    @Autowired
    private MemberBookMarkRepository memberBookMarkRepository;

    @Test
    void saveData(){
        Member member1 = memberRepository.save(new Member("sample1@gmail.com", "sample1", "sample1"));
        Member member2 = memberRepository.save(new Member("sample2@gmail.com","sample2","sample2"));

        BookMark bookMark1 = bookMarkRepository.save(new BookMark("header1", "http://link1", "contents1. hello,bookmark! bye. content"));
        bookMark1.setCategory("computer science","algorithm");

        BookMark bookMark2 = bookMarkRepository.save(new BookMark("header2", "http://link2", "contents2"));
        bookMark2.setCategory("data science","CNN");

        MemberBookMark memberBookMark1 =
                memberBookMarkRepository.save(new MemberBookMark(member1.getId(),bookMark1.getId(),"title1"));
        MemberBookMark memberBookMark2 =
                memberBookMarkRepository.save(new MemberBookMark(member1.getId(),bookMark2.getId(),"title2"));

        List<MemberBookMarkCategoryQueryDto> queryResult = categoryQueryService.query(new MemberBookMarkQueryRequest(member1.getId()));

        System.out.println(queryResult);
        Assertions.assertThat(queryResult.size()).isEqualTo(2);


        Page<BookMarkSearchDto> searchResult = searchService.search(new MemberBookMarkSearchRequest(member1.getId(), "algorithm"), PageRequest.of(0, 20));

        Assertions.assertThat(searchResult.getTotalElements()).isEqualTo(1);
        System.out.println(searchResult.getContent());


    }


}