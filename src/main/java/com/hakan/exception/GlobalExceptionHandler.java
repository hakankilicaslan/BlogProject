package com.hakan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleDemoException(BlogException exception){
        return ResponseEntity.
                status(exception.getErrorType().getHttpStatus()).
                body(ErrorMessage.builder()
                        .code(exception.getErrorType().getCode())
                        .message(exception.getErrorType().getMessage())
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.ok(ErrorMessage.builder()
                .code(9999)
                .message("Beklenmeyen runtime hatası "+exception.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.ok(ErrorMessage.builder()
                .code(6666)
                .message("Değerler uyuşmuyor... "+exception.getMessage())
                .build());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(ArithmeticException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(Exception ex) {
        System.out.println(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()), httpStatus);
    }

    private ErrorMessage createError(BlogException ex){
        return ErrorMessage.builder()
                .code(ex.getErrorType().getCode())
                .message(ex.getMessage())
                .build();
    }

    private ErrorMessage createError(Exception ex, int value){
        return ErrorMessage.builder()
                .code(value)
                .message(ex.getMessage())
                .build();
    }

}
