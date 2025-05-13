package org.sopt.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.global.message.business.DefaultErrorMessage;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
  private final DefaultErrorMessage errorMessage;
}
