package com.test.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.test.entity.CheckFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.test.utils.FileUtil.createDir;
import static com.test.utils.FileUtil.fileDown;
import static com.test.utils.StringUtil.*;


public class FileDown {

    /**
     * @param url
     * @param path
     * @param list
     */
    public static void checkFileDown(String url, String path, List<CheckFile> list) {
        String checkTime;
        String plateNum;
        String iwrId;
        String fileName;
        String downURL;
        for (CheckFile cf : list) {
            checkTime = cf.getCheckTime();
            plateNum = cf.getPlateNum();
            iwrId = cf.getIwrId();
            checkTime = ExtractnNum(checkTime);
            fileName = plateNum + "(" + checkTime + ")" + ".docx";
            downURL = url + iwrId + "&governType=0";
            fileDown(downURL, path, fileName);
            //System.out.println(fileName + "  下载成功");
        }
    }

    /**
     * 下载过车视频和检测单
     *
     * @param mapList 车辆信息的map数据结构
     * @param baseURL 检测单下载URL
     * @param path    下载的检测单保存路径
     */
    public static void downLoad(List<Map<String, String>> mapList, String baseURL, String path) {
        // 检测单保存目录
        String filePath = path + "\\检测单\\";
        // 过车视频和照片保存目录目录
        String videoPath = path + "\\过车视频-照片\\";
        String sedDir;
        createDir(filePath);
        createDir(videoPath);
        for (Map<String, String> map : mapList) {
            String iwrId = map.get("iwrId");
            String checkTime = map.get("checkTime");
            checkTime = reName(checkTime);
            String siteCode = map.get("siteCode");
            String plateNum = map.get("plateNum");
            String videoURL = map.get("fileURL");
            String fileURL = baseURL + iwrId + "&governType=0";
            //String substring = videoURL.substring(0, videoURL.length() - 1);
            String[] allURL = replaceNull(videoURL.split(",+"));
            // 重新定义检测单文件名字, 格式：检测时间-(车牌号)
            String name = checkTime + "(" + plateNum + ")";
            //  再建立一层（车牌号+时间）目录保存照片和视频，如："D:\xx\过车视频-照片\苏A2B650(20211225)"
            sedDir = videoPath + name + "\\";
            createDir(sedDir);
            for (String url : allURL) {
                String videoName = url.substring(url.lastIndexOf('/') + 1);
                // 下载过车图片和视频
                fileDown(url, sedDir, videoName);
            }
            //下载检测单
            fileDown(fileURL, filePath, name + ".docx");
            System.out.println(checkTime + " " + plateNum + " 下载成功");
        }

    }
}
