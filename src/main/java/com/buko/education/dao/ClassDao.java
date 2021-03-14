package com.buko.education.dao;

import com.buko.education.vo.ShowClassVO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 徐健威
 */
@Repository
public interface ClassDao {
    /**
     * 获取班级信息及该班学生信息
     * @param teacherId 导员工号
     * @return 班级信息
     */
    @Select("select id, dept " +
            "where teacher_id = #{teacher_id} limit 1")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "students",
                    many = @Many(select = "com.buko.education.dao.queryStudents"))
    })
    ShowClassVO getStudents(String teacherId);
}
