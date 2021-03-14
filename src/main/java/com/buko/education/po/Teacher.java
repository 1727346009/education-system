package com.buko.education.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author buko
 */
@Data
public class Teacher implements Serializable {
    public String id;

    public String password;
}
