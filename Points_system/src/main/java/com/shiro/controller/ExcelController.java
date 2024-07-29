package com.shiro.controller;
import com.alibaba.excel.EasyExcel;
import com.shiro.entity.BillingNo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @date 2022/1/6 14:07
 */
@Controller
@RequestMapping("myController")
public class ExcelController {
    @GetMapping("/UpdateToken")
    public static void main(String[] args) {
        ExcelListener excelListener = new ExcelListener();
        String inputfile = "C:\\Users\\42900\\Desktop\\日常申请书系统精品课_需求统计.xlsx";
//        String outputfile = "C:\\Users\\yang\\Downloads\\output_saletype.xlsx";
        //读取信息
        EasyExcel.read(inputfile, BillingNo.class, excelListener).sheet().doRead();

        List<BillingNo> dd = excelListener.getSaleType();
        System.out.println("s:"+ dd);

//        //写入信息
//        EasyExcel.write(outputfile,BillingNo.class)
//                .sheet("业态分析")
//                .doWrite(dd);
    }
}
