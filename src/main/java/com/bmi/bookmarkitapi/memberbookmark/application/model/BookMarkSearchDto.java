package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Data
public class BookMarkSearchDto {
    public Long memberBookMarkId;
    public String category;
    public String title;
    public String link;
    public String content;

    public BookMarkSearchDto(Long memberBookMarkId, String category, String title, String link, String content) {
        this.memberBookMarkId = memberBookMarkId;
        this.category = category;
        this.title = title;
        this.link = link;
        this.content = content;
    }

    public String contentSummary(String searchText){
        List<String> sentence = Arrays.asList(this.getContent().split("(?<=[a-z])\\.\\s+"));
        OptionalInt matchIndex = IntStream.range(0, sentence.size())
                .filter(index -> sentence.get(index).contains(searchText))
                .findFirst();
        if (matchIndex.isPresent()){
            int index = matchIndex.getAsInt();
            if (index != sentence.size()-1){
                this.setContent(sentence.get(index)+sentence.get(index+1));
            }
            else{
                this.setContent(sentence.get(index-1)+sentence.get(index));
            }
        }
        else{
            this.setContent(sentence.get(0)+sentence.get(1));
        }
        return  this.getContent();
    }
}
