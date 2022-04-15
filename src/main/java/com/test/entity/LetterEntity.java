package com.test.entity;

public class LetterEntity {

    // 检测单编号
    private String numberId;

    // 检测站点
    private String siteCode;
    // 检测时间
    private String checkTime;
    // 车牌号
    private String plateNum;

    // 车牌颜色
    private String plateColor;


    // 轴数
    private String axleNum;
    // 总重
    private String totalWeight;
    // 超限吨位
    private String overLoad;
    // 超限比例
    private String overRate;


    // 业户名称
    private String ownerName;

    // 车辆所属地址
    private String address;

    // 负责人手机
    private String principalMobile;

    // 审核意见
    private String remark;


    public String getPrincipalMobile() {
        return principalMobile;
    }

    public void setPrincipalMobile(String principalMobile) {
        this.principalMobile = principalMobile;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String time) {
        this.checkTime = time;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getAxleNum() {
        return axleNum;
    }

    public void setAxleNum(String axleNum) {
        this.axleNum = axleNum;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getOverRate() {
        return overRate;
    }

    public void setOverRate(String overRate) {
        this.overRate = overRate;
    }

    public String getOverLoad() {
        return overLoad;
    }

    public void setOverLoad(String overLoad) {
        this.overLoad = overLoad;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactTel() {
        return principalMobile;
    }

    public void setContactTel(String contactTel) {
        this.principalMobile = contactTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    @Override
    public String toString() {
        return "ExportVehInfo{" +
                "siteCode='" + siteCode + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", plateNum='" + plateNum + '\'' +
                ", plateColor='" + plateColor + '\'' +
                ", axleNum='" + axleNum + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                ", overLoad='" + overLoad + '\'' +
                ", overRate='" + overRate + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", address='" + address + '\'' +
                ", principalMobile='" + principalMobile + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }


}
