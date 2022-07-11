package com.test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import static com.test.service.DataAcquisition.getIwrId;
import static com.test.utils.EnumSiteCode.getSiteCodeList;

/**
 * 这个脚本，其实是想获取每辆过车的iwrld，然后拼接证据复核页面的URL，打印出符合条件的过车数据，完成人工审核
 */
public class RunMain2 {

    public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
        // 非现场证据复核页面
        String casePreURL = "http://172.28.171.11:800/api/off/site/getOffSiteIllegal?";

        //  查询日期（开始）  年-月-日
        String beginTime = "2022-03-01";
        //  查询日期（截止）  年-月-日
        String endTime = "2022-03-31";
        // 页码，默认为1不修改
        int pageNum = 1;
        // 每页显示的数据数，默认数据不修改！
        int pageSize = 1000000000;
        // 站点
        List<Map<String, String>> siteCodeList = getSiteCodeList();

        String URL = "http://172.28.171.11:800/#/index/NotDetailed?iwrId=";
        String infoURL;
        // 遍历站点，每个站点导出或下载一个文件
        for (Map<String, String> mapList : siteCodeList) {
            for (String key : mapList.keySet()) {
                String siteCode = mapList.get(key);
                List<String> vehMapList = getIwrId(casePreURL, siteCode, beginTime, endTime, pageNum, pageSize);
                System.out.println(vehMapList.size());
                for (String str : vehMapList) {
                    infoURL = URL + str + "&governType=0";
                    System.out.println(infoURL);
                }
            }
        }
    }
}