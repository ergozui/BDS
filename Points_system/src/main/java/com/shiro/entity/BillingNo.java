package com.shiro.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author yang
 * @version 1.0
 * @date 2022/1/6 14:43
 */
@Data
public class BillingNo {

    @ExcelProperty(index = 0,value = "oldorderid")
    private String oldorderid;

    @ExcelProperty(index = 1,value = "oldordertype")
    private String oldordertype;

    @ExcelProperty(index = 2,value = "oldbillingno")
    private String oldbillingno;

    @ExcelProperty(index = 3,value = "neworderid")
    private String neworderid;

    @ExcelProperty(index = 4,value = "newordertype")
    private String newordertype;

    @ExcelProperty(index = 5,value = "saletype")
    private String saletype;

}