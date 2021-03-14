package com.buko.education.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author buko
 */
@Data
public class Student implements Serializable {
    private String id;

    private String name;

    private String password;

    private Integer classId;

    private String phoneNumber;
}
