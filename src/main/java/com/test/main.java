package com.test;

import com.alibaba.fastjson.JSONObject;
import  java.net.URLEncoder ;

import java.util.List;
import java.util.Map;

import static com.test.utils.EnumSiteCode.getSiteCodeList;
import static com.test.utils.HttpsUtil.doGet;
import static com.test.utils.JsonUtil.getSize;
import static com.test.utils.DateUtil.getDateList;


// 根据证据复核接口查询数据
public class main {
    public static void main(String[] args) throws Exception {

        // 证据复核查询接口
        String ip = "172.28.171.11:800/api/off/site/getOffSiteIllegal?";
        // 站点名
        String siteCode = "S122 K159+500云亭江阴大道东向西";
        //  查询日期（开始）
        String beginTime = "20211201";
        //  查询日期（截止）
        String endTime = "20220119";

        // 页码
        int pageNum = 1;
        // 每页显示的数据数
        int pageSize = 1000000000;
        // 下载文件的保存路径
        String saveDir = "E:\\WorkFile";
        List<String> dateList = getDateList(beginTime, endTime);
        // 遍历每个站点
        List<Map<String, String>> list = getSiteCodeList();
        for (Map<String, String> mapList : list) {
            for (String key : mapList.keySet()) {
                String currentSite = mapList.get(key);
                // 对中文的站点名进行编码
                String urlEncoder = URLEncoder.encode(currentSite, "UTF-8");
                int sum = 0;
                // 遍历日期
                for (int i = 0; i < dateList.size(); i++) {
                    String integralUrl = "http://172.28.171.11:800/api/off/site/getOffSiteIllegal?" +
                            "pageNum=" + pageNum +
                            "&pageSize=" + pageSize +
                            "&siteCode=" + urlEncoder +
                            "&plateNum=" +
                            "&plateColor=" +
                            "&checkTime=" + dateList.get(i) +
                            "&overLoadWeight=&ownerName=&plateTitle=&plateTel=&flag=";
                    JSONObject jsonObject = doGet(integralUrl);
                    int size = getSize(jsonObject);
                    System.out.println(dateList.get(i) + "需要审核的车辆总数为：" + size);
                    sum += size;
                }
                System.out.println("当前站点为：" + currentSite + "需审核总数为：" + sum);
            }
        }
    }
}
