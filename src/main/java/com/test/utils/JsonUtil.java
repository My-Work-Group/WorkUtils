package com.test.utils;

import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import static com.test.utils.DateUtil.getDateList;
import static com.test.utils.HttpsUtil.doGet;
import static com.test.utils.StringUtil.ExtractnNum;

public class JsonUtil {

    // 获取车辆总数
    public static int getSize(JSONObject jsonObject) {
        JSONObject a = jsonObject.getJSONObject("data");
        int size = a.getInteger("total");
        return size;
    }

    //  获取证据复审里全部车辆json数据结构体
    public static List<JSONObject> reVehJson(String revUrl, String siteCode, String beginTime,
                                             String endTime, int pageNum, int pageSize) throws ParseException, UnsupportedEncodingException {

        List<JSONObject> list = new ArrayList<>();
        // 日期list
        List<String> dateList = getDateList(beginTime, endTime);
        // 返回信息
        JSONObject result = new JSONObject();
        // 对中文的站点名进行编码
        String urlEncoder = URLEncoder.encode(siteCode, "UTF-8");
        // 遍历每一天
        for (String s : dateList) {
            String EviRevUrl = revUrl +
                    "pageNum=" + pageNum +
                    "&pageSize=" + pageSize +
                    "&siteCode=" + urlEncoder +
                    "&plateNum=" +
                    "&plateColor=" +
                    "&checkTime=" + s +
                    "&overLoadWeight=&ownerName=&plateTitle=&plateTel=&flag=";
            System.out.println(EviRevUrl);
            result = doGet(EviRevUrl);
            list.add(result);
        }
        return list;
    }

    //  获取证审核通过的全部车辆json数据结构体
    public static List<JSONObject> passVehJson(String revUrl, String siteCode, String beginTime,
                                               String endTime, int pageNum, int pageSize) throws ParseException, UnsupportedEncodingException {

        List<JSONObject> list = new ArrayList<>();

        // 日期list
        List<String> dateList = getDateList(beginTime, endTime);
        // 返回信息
        JSONObject result;
        // 对中文的站点名进行编码
        String urlEncoder = URLEncoder.encode(siteCode, "UTF-8");
        // 遍历每一天
        for (String s : dateList) {
            String EviRevUrl = revUrl +
                    "pageNum=" + pageNum +
                    "&pageSize=" + pageSize +
                    "&siteCode=" + urlEncoder +
                    "&plateNum=" +
                    "&plateColor=" +
                    "&checkTime=" + s +
                    "&caseType=0&plateTitle=&plateTel=";
            result = doGet(EviRevUrl);
            list.add(result);
        }
        return list;
    }

}