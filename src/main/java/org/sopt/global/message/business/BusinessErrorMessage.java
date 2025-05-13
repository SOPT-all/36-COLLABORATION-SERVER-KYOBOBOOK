package org.sopt.global.message.business;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage implements DefaultErrorMessage {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다."),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "잘못된 인자값입니다.");

    private final HttpStatus httpStatus;
    private final String message;


}
