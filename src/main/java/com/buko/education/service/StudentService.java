package com.buko.education.service;

import com.buko.education.po.Student;
import com.buko.education.po.Token;
import com.buko.education.vo.ShowStudentVO;

/**
 * @author 徐健威
 */
public interface StudentService {
    /**
     * 查询学生的详细信息
     * @param id 学号
     * @return 学生信息
     */
    ShowStudentVO queryStudent(String id);
    /**
     * 登录
     * @param student 登录信息
     * @param host ipAddress
     * @return 学生信息
     */
    Token loginStudent(Student student, String host);
}
