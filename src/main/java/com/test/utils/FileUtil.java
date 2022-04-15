package com.test.utils;
import com.test.entity.ExportVehInfo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import  java.net.URLEncoder ;

public class FileUtil {
    public static void createDir(String destDirName) {
        File dir = new File(destDirName);
        if (!dir.exists()) {// 判断目录是否存在
            //dir.mkdir();
            dir.mkdirs();  //多层目录需要调用mkdirs
        }
    }

    public static void ToExcel(List<ExportVehInfo> overVehicleList, String path) {
        if(overVehicleList.size() == 0){
            return;
        }
        //List转为JSONArray
        JSONArray jsonArray = JSONArray.fromObject(overVehicleList);
        //实体属性名称数组
        Field[] fields = null;
        String siteCode = jsonArray.getJSONObject(0).getString("siteCode");
        try {
            Class clazz = Class.forName("com.test.entity.ExportVehInfo");
            fields = clazz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] title = {"检测站点", "检测时间", "车牌", "车牌颜色","轴数", "总重T", "超限T", "超限比例%", "运输公司", "地址", "联系电话","审核意见"};
        if (fields != null) {
            String fileName = siteCode + ".xls"; //文件名
            File myFile = new File(path + fileName);
            try {
                WritableWorkbook writableWorkbook = Workbook.createWorkbook(myFile); //定义工作簿对象
                WritableSheet writableSheet = writableWorkbook.createSheet("sheet1", 0); //定义sheet对象

                for (int i = 0; i < title.length; i++) { //加入表头单元格内容
                    writableSheet.addCell(new Label(i, 0, title[i]));
                }
                for (int i = 0; i < jsonArray.size(); i++) { //加入数据单元格内容
                    JSONObject json = jsonArray.getJSONObject(i);
                    for (int j = 0; j < fields.length; j++) {
                        writableSheet.addCell(new Label(j, i + 1, json.get(fields[j].getName()).toString()));
                    }
                }
                writableWorkbook.write();
                writableWorkbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据链接地址下载文件
     *
     * @param downloadUrl 文件链接地址
     * @param savePath    保存文件地址
     * @param fileName    文件名
     */
    public static void fileDown(String downloadUrl, String savePath, String fileName) {
        URL url = null;
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(downloadUrl);
            dataInputStream = new DataInputStream(url.openStream());
            fileOutputStream = new FileOutputStream(savePath + fileName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


