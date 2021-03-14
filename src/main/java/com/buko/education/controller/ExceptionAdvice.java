package com.buko.education.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.buko.education.dto.RequestResult;
import com.buko.education.enumerate.StatusCodeEnum;
import com.buko.education.exception.AuthenticateException;
import com.buko.education.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 * @author 徐健威
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {BaseException.class})
    public RequestResult<String> baseHandler(RuntimeException e) {
        return new RequestResult<>(StatusCodeEnum.DOES_NOT_EXIST, e.getMessage());
    }

    @ExceptionHandler(value = {JWTVerificationException.class})
    public RequestResult<String> jwtHandler(JWTVerificationException e) {
        log.debug(e.getMessage());
        return new RequestResult<>(StatusCodeEnum.FORBIDDEN);
    }

    @ExceptionHandler(value = {AuthenticateException.class})
    public RequestResult<String> authHandler(AuthenticateException e) {
        return new RequestResult<>(StatusCodeEnum.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public RequestResult<String> checkHandler(Exception e) {
        log.debug(e.getMessage());
        return new RequestResult<>(StatusCodeEnum.SYS_INNER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public RequestResult<String> commonHandle(Exception e) {
        return new RequestResult<>(StatusCodeEnum.DOES_NOT_EXIST, e.getMessage());
    }
}
