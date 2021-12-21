package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.IMemberBookMarkSearchRepository;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class MemberBookMarkQueryService extends BaseQueryService<MemberBookmark> {
    private final IMemberBookMarkSearchRepository searchRepository;

    public MemberBookMarkQueryService(MemberBookMarkRepository repository, IMemberBookMarkSearchRepository searchRepository) {
        super(repository, new MemberBookMarkNotFoundException());
        this.searchRepository = searchRepository;
    }

    public Page<MemberBookMarkSearchResponse> query(MemberBookMarkSearchRequest searchRequest, Pageable pageable){
        Page<MemberBookMarkSearchResponse> result = searchRepository.search(searchRequest,pageable);
        result.forEach(response -> response.setContent(contentSummary(response.getContent(),searchRequest.getSearchText())));
        return result;
    }

    public String contentSummary(String content,String searchText){
        List<String> sentence = Arrays.asList(content.split("(?<=[a-z])\\.\\s+"));
        OptionalInt matchIndex = IntStream.range(0, sentence.size())
                .filter(index -> sentence.get(index).contains(searchText))
                .findFirst();
        if (matchIndex.isPresent()){
            int index = matchIndex.getAsInt();
            if (index != sentence.size()-1){
                return sentence.get(index)+sentence.get(index+1);
            }
            else{
                return sentence.get(index-1)+sentence.get(index);
            }
        }
        else{
            return sentence.get(0)+sentence.get(1);
        }

    }


}
