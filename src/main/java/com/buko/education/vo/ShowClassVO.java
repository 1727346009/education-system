package com.buko.education.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 徐健威
 */
@Data
public class ShowClassVO {
    private Integer id;

    private String dept;

    List<ShowStudentVO> students;
}
