package com.bmi.bookmarkitapi.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public class Response {

    private static final String OK_MESSAGE = "정상적으로 처리되었습니다.";

    @Getter
    public static class Item<T> {
        private final boolean success;
        private final String message;
        private final T result;

        public Item(T result) {
            success = true;
            message = OK_MESSAGE;
            this.result = result;
        }
    }

    @Getter
    public static class ItemList<T> {
        private final boolean success;
        private final String message;
        private final List<T> result;
        private final int totalElements;

        public ItemList(List<T> result) {
            success = true;
            message = OK_MESSAGE;
            this.result = result;
            totalElements = result.size();
        }
    }

    @Getter
    public static class ItemPage<T> {
        private final boolean success;
        private final String message;
        private final List<T> result;
        private final long totalElements;
        private final int totalPages;

        public ItemPage(Page<T> page) {
            success = true;
            message = OK_MESSAGE;
            result = page.getContent();
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
        }
    }

    @Getter
    public static class Empty {
        private final boolean success;
        private final String message;

        public Empty() {
            success = true;
            message = OK_MESSAGE;
        }
    }

    @Getter
    public static class Error {
        private final boolean success;
        private final String message;

        public Error(String message) {
            success = false;
            this.message = message;
        }
    }
}
