package com.yc.demo.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class User implements Serializable {
    private Long id;
    private String userName;
    private Integer age;
    private LocalDateTime createTime;
}
