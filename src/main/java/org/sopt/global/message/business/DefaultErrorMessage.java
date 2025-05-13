package org.sopt.global.message.business;

import org.springframework.http.HttpStatus;

public interface DefaultErrorMessage {
    HttpStatus getHttpStatus();
    String getMessage();
}
