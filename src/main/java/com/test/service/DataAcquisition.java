package com.test.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.CheckFile;
import com.test.entity.ExportVehInfo;
import com.test.entity.VehEntity;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

import static com.test.utils.HttpsUtil.doGet;
import static com.test.utils.JsonUtil.passVehJson;
import static com.test.utils.JsonUtil.reVehJson;

// 数据获取
public class DataAcquisition {

    // 获取超限车辆自定义的对象信息
    public static ExportVehInfo getVechInfo(String vehInfoUrl, String weightURL) {
        // 获取车辆的重量信息
        JSONObject weightInfoJson = doGet(weightURL);
        // 根据iwrId获取车辆的详情信息
        JSONObject vehInfoJson = doGet(vehInfoUrl);
        JSONObject a = weightInfoJson.getJSONObject("data");
        JSONObject b = vehInfoJson.getJSONObject("data");
        ExportVehInfo overInfo = new ExportVehInfo();
        // 检测站点
        String siteCode = a.getString("siteCode");
        // 检测时间
        String checkTime = a.getString("checkTime");
        // 车牌号
        String plateNum = a.getString("plateNum");

//        // 车辆吨位里面存在车牌号为空的，只能去车辆信息界面里获取车牌号
//        if (plateNum == null || "".equals(plateNum)) {
//            plateNum = b.getString("vehicleNum");
//        }
        // 轴数
        String axleNum = a.getString("axleNum");
        // 总重
        String totalWeight = a.getString("totalWeight");
        // 超限比例
        String overRate = a.getString("overRate");
        // 超限吨位
        String overLoad = a.getString("overLoad");
        // 车辆所属地址
        String address = b.getString("address");
        // 业户名称
        String ownerName = b.getString("ownerName");
        // 联系电话
        String principalMobile = b.getString("principalMobile");
        overInfo.setSiteCode(siteCode);
        overInfo.setCheckTime(checkTime);
        overInfo.setPlateNum(plateNum);
        overInfo.setAxleNum(axleNum);
        overInfo.setTotalWeight(totalWeight);
        overInfo.setOverRate(overRate);
        overInfo.setOverLoad(overLoad);
        overInfo.setAddress(address);
        overInfo.setOwnerName(ownerName);
        overInfo.setContactTel(principalMobile);
        return overInfo;
    }

    /**
     * 获取超限车辆吨位数据 （cpId和iwrId）
     *
     * @param url 非现场处罚的查询接口
     * @return 超限车辆吨位数据
     */
    public static List<VehEntity> getVechEntity(String url, String siteCode, String beginTime,
                                                String endTime, int pageNum, int pageSize) throws UnsupportedEncodingException, ParseException {
        // 获取车辆的重量信息
        List<JSONObject> reVehList = passVehJson(url, siteCode, beginTime, endTime, pageNum, pageSize);
        List<VehEntity> vehEntityList = new ArrayList<>();
        for (JSONObject jsonObject : reVehList) {
            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
            // 跳过为list为空（有可能某一天无符合超载条件的车辆）
            if (jsonObject.getJSONObject("data").getInteger("total") == 0) {
                continue;
            }
            for (int i = 0; i < list.size(); i++) {
                VehEntity vehEntity = new VehEntity();
                JSONObject vehInfo = list.getJSONObject(i);
                vehEntity.setCpId(vehInfo.getString("cpId"));
                vehEntity.setIwrId(vehInfo.getString("iwrId"));
                vehEntity.setCheckTime(vehInfo.getString("checkTime"));
                vehEntity.setSiteCode(vehInfo.getString("siteCode"));
                vehEntity.setSiteName(vehInfo.getString("siteName"));
                vehEntity.setPlateNum(vehInfo.getString("plateNum"));
                vehEntity.setPlateCol(vehInfo.getString("plateColor"));
                vehEntity.setAxleNum(vehInfo.getString("axleNum"));
                vehEntity.setVehicleTypeStr(vehInfo.getString("vehicleTypeStr"));
                vehEntity.setMaxLoad(vehInfo.getString("maxLoad"));
                vehEntity.setTotalWeight(vehInfo.getString("totalWeight"));
                vehEntity.setOverLoad(vehInfo.getString("overLoad"));
                vehEntity.setOverRate(vehInfo.getString("overRate"));
                vehEntity.setReviewStatus(vehInfo.getString("reviewStatus"));
                vehEntity.setProcessStatus(vehInfo.getString("processStatus"));
                vehEntity.setRemark(vehInfo.getString("remark"));
                vehEntityList.add(vehEntity);
            }
        }
        return vehEntityList;
    }

    // 获取车辆信息所需的iwrId
    public static List<String> getIwrId(String revUrl, String siteCode, String beginTime,
                                        String endTime, int pageNum, int pageSize) throws ParseException, UnsupportedEncodingException {
        List<String> iwrIdList = new ArrayList();
        List<JSONObject> reVehList = passVehJson(revUrl, siteCode, beginTime, endTime, pageNum, pageSize);
        for (JSONObject jsonObject : reVehList) {
            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
            // 跳过为list为空（有可能某一天无符合超载条件的车辆）
            if (jsonObject.getJSONObject("data").getInteger("total") == 0) {
                continue;
            }
            for (int j = 0; j < list.size(); j++) {
                //String plateNum = list.getJSONObject(j).getString("plateNum");
                // 跳过空车牌号
//                if (list.getJSONObject(j).getString("plateNum") == null || "".equals(plateNum)) {
//                    continue;
//                }
                iwrIdList.add(list.getJSONObject(j).getString("iwrId"));
            }
        }
        return iwrIdList;
    }

    // 获取所需的信息，返回导出车辆详情的json数据结构体
    public static List<ExportVehInfo> getVehInfo(String vehURL, String transURL, String siteCode, String beginTime,
                                                 String endTime, int pageNum, int pageSize) throws UnsupportedEncodingException, ParseException {
        // 自定义车辆信息对象的List
        List<ExportVehInfo> vehInfoList = new ArrayList<>();
        List<VehEntity> vehEntityList = getVechEntity(vehURL, siteCode, beginTime, endTime, pageNum, pageSize);

        if (vehEntityList.size() == 0) {
            return null;
        }
        for (VehEntity vehEntity : vehEntityList) {
            ExportVehInfo vehInfo = new ExportVehInfo();
            String iwrId;
            vehInfo.setSiteCode(vehEntity.getSiteCode());
            vehInfo.setCheckTime(vehEntity.getCheckTime());
            vehInfo.setPlateNum(vehEntity.getPlateNum());
            vehInfo.setPlateColor(vehEntity.getPlateCol());
            vehInfo.setAxleNum(vehEntity.getAxleNum());
            vehInfo.setTotalWeight(vehEntity.getTotalWeight());
            vehInfo.setOverLoad(vehEntity.getOverLoad());
            vehInfo.setOverRate(vehEntity.getOverRate());
            iwrId = vehEntity.getIwrId();
            // 车辆的运输信息
            Map<String, String> transMap = getTransInfo(transURL, iwrId);
            vehInfo.setAddress(transMap.get("address"));
            vehInfo.setOwnerName(transMap.get("ownerName"));
            vehInfo.setPrincipalMobile(transMap.get("principalMobile"));
            vehInfoList.add(vehInfo);

            //审核意见
            vehInfo.setRemark(vehEntity.getRemark());

        }

        return vehInfoList;
    }


    /**
     * 根据cpId和iwrId获取审核通过车辆的照片和视频下载链接
     *
     * @param cpId
     * @param iwrId
     * @return
     */
    public static String getUrl(String url, String cpId, String iwrId) {
        String videoUrl;
        String infoUrl = url + "weightRegisterId=" + iwrId + "&governType=0&" + "cpId=" + cpId;
        JSONObject result = doGet(infoUrl);
        videoUrl = result.getJSONObject("data").getString("filePath");
        return videoUrl;
    }

    // 获取自定义车辆信息map
    public static List<Map<String, String>> getVehMapList(String vehURL, String vehEntityURL, String siteCode, String beginTime,
                                                          String endTime, int pageNum, int pageSize) throws UnsupportedEncodingException, ParseException {
        List<Map<String, String>> vehMapList = new ArrayList<>();
        List<VehEntity> vehEntityList = getVechEntity(vehURL, siteCode, beginTime, endTime, pageNum, pageSize);
        for (VehEntity vehEntity : vehEntityList) {
            Map<String, String> vehMap = new HashMap<>();
            String allURL = getUrl(vehEntityURL, vehEntity.getCpId(), vehEntity.getIwrId());
            vehMap.put("iwrId", vehEntity.getIwrId());
            vehMap.put("checkTime", vehEntity.getCheckTime());
            vehMap.put("siteCode", vehEntity.getSiteCode());
            vehMap.put("plateNum", vehEntity.getPlateNum());
            vehMap.put("plateCol", vehEntity.getPlateCol());
            vehMap.put("fileURL", allURL);
            vehMap.put("remark", vehEntity.getRemark());
            vehMapList.add(vehMap);
        }
        return vehMapList;
    }

    // 获取所需的信息，返回下载文件实体的list
    public static List<CheckFile> getCheckFileList(String revUrl, String siteCode, String beginTime,
                                                   String endTime, int pageNum, int pageSize) throws UnsupportedEncodingException, ParseException {
        // 自定义检测单对象的List
        List<CheckFile> checkFileList = new ArrayList<>();
        // 证据复核数据返回结果
        List<JSONObject> reVehList = reVehJson(revUrl, siteCode, beginTime, endTime, pageNum, pageSize);
        // 解析所有站点数据审核list
        for (JSONObject jsonObject : reVehList) {
            JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
            // 遍历list
            for (int j = 0; j < list.size(); j++) {
                CheckFile checkFile = new CheckFile();
                String plateNum = list.getJSONObject(j).getString("plateNum");
                // 跳过空车牌号
                if (plateNum == null || "".equals(plateNum)) {
                    continue;
                }
                String checkTime = list.getJSONObject(j).getString("checkTime");
                String iwrId = list.getJSONObject(j).getString("iwrId");
                checkFile.setCheckTime(checkTime);
                checkFile.setPlateNum(plateNum);
                checkFile.setIwrId(iwrId);
                checkFileList.add(checkFile);
            }
        }
        return checkFileList;
    }

    /**
     * 根据 iWrld获取车辆的运输信息
     *
     * @param url   车辆运输查询的URL
     * @param iWrld
     * @return
     */
    public static Map<String, String> getTransInfo(String url, String iWrld) {
        // 根据iwrId获取车辆的详情信息
        url = url + iWrld;
        JSONObject transInfo = doGet(url).getJSONObject("data");
        Map<String, String> transInfoMap = new HashMap<>();
        // 车辆所属地址
        String address = transInfo.getString("address");
        // 业户名称
        String ownerName = transInfo.getString("ownerName");
        // 联系电话
        String principalMobile = transInfo.getString("principalMobile");
        transInfoMap.put("address", address);
        transInfoMap.put("ownerName", ownerName);
        transInfoMap.put("principalMobile", principalMobile);
        return transInfoMap;
    }
}