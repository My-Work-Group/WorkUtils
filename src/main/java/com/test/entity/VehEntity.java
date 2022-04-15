package com.test.entity;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

// 超限车辆重量实体信息
public class VehEntity {

    private String cpId;
    private String iwrId;
    private String checkTime;
    private String siteCode;
    private String siteName;
    private String plateNum;
    private String plateCol;
    private String axleNum;
    private String vehicleTypeStr;
    private String maxLoad;
    private String totalWeight;
    private String overLoad;
    private String overRate;
    private String reviewStatus;
    private String processStatus;
    private String remark;

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getIwrId() {
        return iwrId;
    }

    public void setIwrId(String iwrId) {
        this.iwrId = iwrId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getPlateCol() {
        return plateCol;
    }

    public void setPlateCol(String plateCol) {
        this.plateCol = plateCol;
    }

    public String getAxleNum() {
        return axleNum;
    }

    public void setAxleNum(String axleNum) {
        this.axleNum = axleNum;
    }

    public String getVehicleTypeStr() {
        return vehicleTypeStr;
    }

    public void setVehicleTypeStr(String vehicleTypeStr) {
        this.vehicleTypeStr = vehicleTypeStr;
    }

    public String getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(String maxLoad) {
        this.maxLoad = maxLoad;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getOverLoad() {
        return overLoad;
    }

    public void setOverLoad(String overLoad) {
        this.overLoad = overLoad;
    }

    public String getOverRate() {
        return overRate;
    }

    public void setOverRate(String overRate) {
        this.overRate = overRate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "VehEntity{" +
                "cpId='" + cpId + '\'' +
                ", iwrId='" + iwrId + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", siteCode='" + siteCode + '\'' +
                ", siteName='" + siteName + '\'' +
                ", plateNum='" + plateNum + '\'' +
                ", plateCol='" + plateCol + '\'' +
                ", axleNum='" + axleNum + '\'' +
                ", vehicleTypeStr='" + vehicleTypeStr + '\'' +
                ", maxLoad='" + maxLoad + '\'' +
                ", totalWeight='" + totalWeight + '\'' +
                ", overLoad='" + overLoad + '\'' +
                ", overRate='" + overRate + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", processStatus='" + processStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
