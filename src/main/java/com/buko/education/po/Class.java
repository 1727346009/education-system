package com.buko.education.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 徐健威
 */
@Data
public class Class implements Serializable {
    private Integer id;

    private Integer teacherId;

    private String dept;
}
