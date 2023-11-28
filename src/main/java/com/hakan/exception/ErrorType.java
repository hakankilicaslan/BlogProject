package com.hakan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorType {

    USER_NOT_FOUND(4001,"Böyle bir kullanıcı bulunamamıştır...", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(4002,"Böyle bir post bulunamamıştır...",HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND(4003,"Böyle bir yorum bulunamamıştır...",HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(4004,"Böyle bir kategori bulunamamıştır...", HttpStatus.NOT_FOUND),
    PARAMETER_MISSING(4005,"Parametreler eksik girilmiştir. Lütfen kontrol ediniz...",HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(4006,"Şifre geçersizdir. Lütfen şifrenizi kontrol ediniz...",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXSIST(4007,"Bu email adresi zaten kullanılıyor. Lütfen başka bir email adresi giriniz...",HttpStatus.ALREADY_REPORTED);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
