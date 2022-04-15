package com.test;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

import static com.test.utils.EnumSiteCode.getSiteCodeList;
import static com.test.utils.HttpsUtil.send;



// 根据综合查询接口查询数据
public class main2 {
    public static void main(String[] args) throws Exception {

        // 证据复核查询接口
        String url = "http://180.101.184.120:63216/api/complex/searchRegister?pageNum=1&pageSize=1000";

        //  查询日期（开始）
        String startTime = "2021-12-25";
        //  查询日期（截止）
        String endTime = "2022-01-19";
        // 遍历每个站点
        List<Map<String, String>> list = getSiteCodeList();
        for (Map<String, String> mapList : list) {
            // 创建发送的json数据结构体
            JSONObject jsonParam = new JSONObject();
            for (String key : mapList.keySet()) {
                String currentSite = mapList.get(key);
                jsonParam.put("siteCode", currentSite);
                jsonParam.put("startTime", startTime);
                jsonParam.put("endTime", endTime);
                String result = send(url, jsonParam, "utf-8");
                JSONObject jsonObject = JSONObject.parseObject(result);
                //getNotNullSize(jsonObject);
                //System.out.println("当前站点为：" + currentSite + "需审核总数为：" + totalNum);
            }
        }
    }
}
