package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.*;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkCategoryQueryService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkListQueryService listQueryService;

    public Map<String, Map<String, List<BookMarkQueryDto>>> query(MemberBookMarkQueryRequest queryRequest){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookMarkIdList = memberBookMarkList
                .stream()
                .map(MemberBookMark::getMemberId)
                .collect(Collectors.toList());

        BookMarkCategoryQueryRequest request = new BookMarkCategoryQueryRequest(
                bookMarkIdList,
                queryRequest.getMainCategory());

        List<BookMark> queryResult = listQueryService.query(request);


        List<BookMarkQueryDto> bookMarkList = new ArrayList<>();

        queryResult.forEach(bookMark -> {
            Optional<MemberBookMark> memberBookMark = memberBookMarkList.stream()
                    .filter(mbm -> Objects.equals(mbm.getBookmarkId(), bookMark.getId()))
                    .findFirst();
            memberBookMark.ifPresent(mbm -> {
                BookMarkQueryDto bookMarkQueryDto = new BookMarkQueryDto(
                        bookMark.getMainCategory(),
                        bookMark.getSubCategory(),
                        mbm.getTitle(),
                        bookMark.getLink(),
                        bookMark.createDateToString(),
                        bookMark.getStatus());
                bookMarkList.add(bookMarkQueryDto);
            });
        });


        List<MemberBookMarkCategoryQueryDto>  responseResult = new ArrayList<>();
        MemberBookMarkCategoryQueryDto categoryQueryDto = new MemberBookMarkCategoryQueryDto();

        for (BookMarkQueryDto dto : bookMarkList) {
            if (categoryQueryDto.getMainCategory() == null){
                categoryQueryDto.setMainCategory(dto.getMainCategory());
                categoryQueryDto.bookMarkList.add(dto);
            }
            else if(!Objects.equals(categoryQueryDto.getMainCategory(),
                    dto.getMainCategory())){
                responseResult.add(categoryQueryDto);
                categoryQueryDto = new MemberBookMarkCategoryQueryDto();
                categoryQueryDto.setMainCategory(dto.getMainCategory());
                categoryQueryDto.bookMarkList.add(dto);
            }
            else{
                categoryQueryDto.bookMarkList.add(dto);
            }
        }
        if(categoryQueryDto.bookMarkList.size()>=1){
            responseResult.add(categoryQueryDto);
        }

        System.out.println(bookMarkList);

        Map<String, Map<String, List<BookMarkQueryDto>>> map = new HashMap<>();

        Map<String, List<BookMarkQueryDto>> groupByMainCategory = bookMarkList.stream()
                .collect(Collectors.groupingBy(BookMarkQueryDto::getMainCategory));

        for (Map.Entry<String, List<BookMarkQueryDto>> entry : groupByMainCategory.entrySet()) {
            Map<String, List<BookMarkQueryDto>> groupBySubCategory = entry.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(BookMarkQueryDto::getSubCategory));

            map.put(entry.getKey(), groupBySubCategory);
        }

        return map;
    }
}
