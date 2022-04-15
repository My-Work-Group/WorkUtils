package com.test.entity;

// 检测单实体
public class CheckFile {

    // 检测时间
    private String checkTime;
    // 车牌号
    private String plateNum;
    // iwrId
    private String iwrId;

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getIwrId() {
        return iwrId;
    }

    public void setIwrId(String iwrId) {
        this.iwrId = iwrId;
    }

    @Override
    public String toString() {
        return "CheckFile{" +
                "checkTime='" + checkTime + '\'' +
                ", plateNum='" + plateNum + '\'' +
                ", iwrId='" + iwrId + '\'' +
                '}';
    }
}
