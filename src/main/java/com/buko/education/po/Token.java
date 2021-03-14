package com.buko.education.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 徐健威
 */
@Data
public class Token implements Serializable {
    private String authenticate;
}
