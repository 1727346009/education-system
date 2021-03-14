package com.buko.education.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 徐健威
 */
public interface TeacherDao {

    /**
     * 导员认证
     * @param id 工号
     * @return 密码
     */
    @Select("select password " +
            "from teacher " +
            "where id = #{id} limit 1")
    String auth(@Param("id") String id);

}
