package com.yc.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author: yinchao
 * @date 2019/11/8
 */
@Data
@ToString
public class RePos {
    private Integer id;
    private String retailer_id;
    private String pos_id;
    private String re_bank_name;
    private String re_bank_number;
    private String re_bank_account_name;
    private String re_bank_account_number;
    private String pos_bank_name;
    private String pos_bank_code;
    private String pos_bank_account_name;
    private String pos_bank_account_number;
}
