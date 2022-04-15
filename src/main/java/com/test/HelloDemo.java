package com.test;

import com.test.entity.CheckFile;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import static com.test.service.DataAcquisition.getCheckFileList;
import static com.test.service.DataAcquisition.getVehInfo;
import static com.test.service.FileDown.checkFileDown;
import static com.test.utils.EnumSiteCode.getSiteCodeList;
import static com.test.utils.FileUtil.ToExcel;
import static com.test.utils.FileUtil.createDir;

// 导出非现场处罚的表格数据
public class HelloDemo {
    public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
        // 证据复核查询接口
        String revUrl = "http://172.28.171.11:800/api/off/site/getOffSiteIllegal?";
        //String revUrl = "http://180.101.184.120:63216/api/off/site/getOffSiteIllegal?";
        //车辆详情
        //String vehInfoUrl = "http://180.101.184.120:63216/api/review/getTransport?governType=0&iwrId=";
        String vehInfoUrl = "http://172.28.171.11:800/api/review/getTransport?governType=0&iwrId=";
        // 检测单下载URL
        String checkFileDownURL = "http://172.28.171.11:800/api/file/download?type=11&iwrId=";
        //检测单保存路径
        String path = "E:\\非现场治超\\";
        //  查询日期（开始）
        String beginTime = "20211225";
        //  查询日期（截止）
        String endTime = "20220120";
        // 页码
        int pageNum = 1;
        // 每页显示的数据数
        int pageSize = 1000000000;
        // 站点
        List<Map<String, String>> siteCodeList = getSiteCodeList();

        // 遍历站点，每个站点导出或下载一个文件
        String savePath;
        for (Map<String, String> mapList : siteCodeList) {
            for (String key : mapList.keySet()) {
                String siteCode = mapList.get(key);
                savePath = path + siteCode +"\\";
                createDir(savePath); // 根据站点名创建目录
                //List<OverVehicle> vehInfo = getVehInfo(revUrl, vehInfoUrl, siteCode, beginTime, endTime, pageNum, pageSize);
                //ToExcel(vehInfo);
                List<CheckFile> checkFileList = getCheckFileList(revUrl, siteCode, beginTime, endTime, pageNum, pageSize);
                checkFileDown(checkFileDownURL, savePath, checkFileList);
            }
        }
    }
}

