package com.buko.education.service;

import com.buko.education.po.Teacher;
import com.buko.education.po.Token;

/**
 * @author 徐健威
 */
public interface TeacherService {
    /**
     * 登录
     * @param teacher 登录信息
     * @param host ipAddress
     * @return 导员信息
     */
    Token loginTeacher(Teacher teacher, String host);
}
