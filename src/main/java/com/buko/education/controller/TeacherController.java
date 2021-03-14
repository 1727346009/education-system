package com.buko.education.controller;

import com.buko.education.annotation.SignIn;
import com.buko.education.dto.RequestResult;
import com.buko.education.enumerate.StatusCodeEnum;
import com.buko.education.po.Teacher;
import com.buko.education.po.Token;
import com.buko.education.service.TeacherService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 徐健威
 */
public class TeacherController {
    @Reference
    private TeacherService teacherService;
    /**
     * 导员登录
     * @param teacher 导员（账号-密码）
     * @param host 请求登录 ip address
     * @return token 明细
     */
    @SignIn
    @PostMapping(value = "/teacher/login")
    public RequestResult<Token> userSignIn(@RequestBody Teacher teacher,
                                           @RequestAttribute("host") String host) {
        Token token = teacherService.loginTeacher(teacher, host);
        return new RequestResult<>(StatusCodeEnum.SUCCESS, token);
    }
}
