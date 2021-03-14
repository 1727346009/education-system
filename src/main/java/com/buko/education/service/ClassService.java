package com.buko.education.service;

import com.buko.education.vo.ShowClassVO;

/**
 * @author buko
 */
public interface ClassService {
    /**
     * 查询整个班级学生的详细信息
     * @param teacherId 工号
     * @return 班级信息包括学生信息
     */
    ShowClassVO queryStudents(String teacherId);
}
