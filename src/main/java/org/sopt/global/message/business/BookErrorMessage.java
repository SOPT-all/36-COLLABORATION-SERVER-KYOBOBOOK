package org.sopt.global.message.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorMessage implements DefaultErrorMessage{
    NOT_FOUND_BOOK(HttpStatus.BAD_REQUEST, "도서 정보가 없습니다."),
    EMPTY_BOOK_INDEX(HttpStatus.BAD_REQUEST, "도서 목차 정보가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;


}
