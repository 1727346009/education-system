package com.buko.education.dao;

import com.buko.education.vo.ShowStudentVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 徐健威
 */
@Repository
public interface StudentDao {
    /**
     * 查询学生信息
     * @param id 学号
     * @return 学生信息
     */
    @Select("select id, name, class_id, phone_number " +
            "from student" +
            "where id = #{id} limit 1")
    ShowStudentVO queryStudent(@Param("id") String id);

    /**
     * 学生认证
     * @param id 学号
     * @return 密码
     */
    @Select("select password " +
            "from student " +
            "where id = #{id} limit 1")
    String auth(@Param("id") String id);

    /**
     * 查询同班级学生信息
     * @param classId 班级
     * @return 学生信息
     */
    @Select("select id, name, phone_number " +
            "from student " +
            "where class_id = #{class_id}")
    List<ShowStudentVO> queryStudents(@Param("class_id") String classId);
}
