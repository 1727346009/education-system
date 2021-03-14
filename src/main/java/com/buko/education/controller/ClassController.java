package com.buko.education.controller;

import com.buko.education.dto.RequestResult;
import com.buko.education.enumerate.StatusCodeEnum;
import com.buko.education.service.ClassService;
import com.buko.education.vo.ShowClassVO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐健威
 */
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class ClassController {
    @Reference
    private ClassService classService;

    @GetMapping(value = "/class")
    public RequestResult<ShowClassVO> getStudents(@RequestAttribute("id") String teacherId) {
        return new RequestResult<>(
                StatusCodeEnum.SUCCESS,
                classService.queryStudents(teacherId)
        );
    }
}
