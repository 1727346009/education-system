package com.buko.education.controller;

import com.buko.education.annotation.SignIn;
import com.buko.education.dto.RequestResult;
import com.buko.education.enumerate.StatusCodeEnum;
import com.buko.education.po.Student;
import com.buko.education.po.Token;
import com.buko.education.service.StudentService;
import com.buko.education.vo.ShowStudentVO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * @author 徐健威
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class StudentController {
    @Reference
    private StudentService studentService;

    /**
     * 用户登录
     * @param student 学生（账号-密码）
     * @param host 请求登录 ip address
     * @return token 明细
     */
    @SignIn
    @PostMapping(value = "/student/login")
    public RequestResult<Token> userSignIn(@RequestBody Student student,
                                           @RequestAttribute("host") String host) {
        Token token = studentService.loginStudent(student, host);
        return new RequestResult<>(StatusCodeEnum.SUCCESS, token);
    }

    @GetMapping(value = "/student")
    public RequestResult<ShowStudentVO> getStudent(@RequestAttribute("id")  String studentId) {
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                studentService.queryStudent(studentId)
        );
    }
}
