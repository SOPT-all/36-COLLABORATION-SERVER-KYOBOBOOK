package org.sopt.global.message.business;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookErrorMesseage {
    BOOK_TITLE_DUPLICATE(HttpStatus.BAD_REQUEST, "책 제목이 중복됩니다."),
    ;

    BookErrorMesseage(HttpStatus httpStatus, String s) {
    }
}
