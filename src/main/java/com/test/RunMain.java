package com.test;
import com.test.entity.ExportVehInfo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.test.service.DataAcquisition.getVehInfo;
import static com.test.service.DataAcquisition.getVehMapList;
import static com.test.service.FileDown.downLoad;
import static com.test.utils.EnumSiteCode.getSiteCodeList;
import static com.test.utils.FileUtil.ToExcel;
import static com.test.utils.FileUtil.createDir;


// 根据审核通过的页面下载检测单，过车视频，过车照片，车辆信息
public class RunMain {

    public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
        // 非现场案件处罚查询接口
        String punURL = "http://172.28.171.11:800/api/case/getCaseForPunishment?";
        //String punURL = "http://180.101.184.120:63216/api/case/getCaseForPunishment?";

        //车辆运输信息详情
        //String vehInfoURL = "http://180.101.184.120:63216/api/review/getTransport?governType=0&iwrId=";
        String transInfoURL = "http://172.28.171.11:800/api/review/getTransport?governType=0&iwrId=";

        // 检测单下载URL
        String checkFileDownURL = "http://172.28.171.11:800/api/file/download?type=11&iwrId=";

        // 照片，视频下载链接
        // String vehEntityURL = "http://180.101.184.120:63216/api/off/site/getReviewDetail?";
        String vehEntityURL = "http://172.28.171.11:800/api/off/site/getReviewDetail?";

        // 保存路径
        String path = "C:\\Users\\pangpd\\Desktop\\非现场治超\\";
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

        List<ExportVehInfo> exportVehInfo = null;
        List<ExportVehInfo> allVehInfo = new ArrayList<>();
        // 遍历站点，每个站点导出或下载一个文件
        String savePath;
        for (Map<String, String> mapList : siteCodeList) {
            for (String key : mapList.keySet()) {
                String siteCode = mapList.get(key);
                // 下载检测单，过车照片和视频
                savePath = path + siteCode + "\\";
                createDir(savePath); // 根据站点名创建目录
//                List<Map<String, String>> vehMapList = getVehMapList(punURL, vehEntityURL, siteCode, beginTime, endTime, pageNum, pageSize);
//                downLoad(vehMapList, checkFileDownURL, savePath);
                exportVehInfo = getVehInfo(punURL, transInfoURL, siteCode, beginTime, endTime, pageNum, pageSize);
                if (exportVehInfo == null){
                    continue;
                }
                allVehInfo.addAll(exportVehInfo);
            }
        }
        //导出超限车辆具体信息到一张excel表中
        if (allVehInfo != null){
            ToExcel(allVehInfo, path);
        }
    }

}
