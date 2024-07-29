package com.shiro.controller;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.shiro.entity.BillingNo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @date 2022/1/6 14:01
 */
public class ExcelListener extends AnalysisEventListener<BillingNo> {

    private List<BillingNo> data = new ArrayList<BillingNo>();

    /**
     * 从第二行开始一行一行读取
     * @param billingNo 实体类
     * @param analysisContext 内容
     */
    @Override
    public void invoke(BillingNo billingNo, AnalysisContext analysisContext) {
        data.add(billingNo);
    }

    /**
     * 读取第一行
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("头信息"+headMap);

    }

    public List<BillingNo> getData() {
        return data;
    }

    public void setData(List<BillingNo> data) {
        this.data = data;
    }

    public List<BillingNo> getSaleType(){
        for (BillingNo bb : data) {
            String oldBillingNo = bb.getOldbillingno();
            if (oldBillingNo != null && oldBillingNo.length() >= 2) {
                bb.setSaletype(oldBillingNo.substring(0, 2));
            } else {
                // 处理长度不足2的情况，可以记录日志或执行其他逻辑
                bb.setSaletype("N/A"); // 设置一个默认值或采取其他适当的处理方式
            }
        }
        return data;
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }


}