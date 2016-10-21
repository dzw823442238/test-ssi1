package com.test.model;

import java.util.Date;

/**
 * Created by LPT12013 on 2016/9/9.
 */
public class DisMapping {
    public String disId;
    public String disCode;
    public String disName;
    public String state;
    public Date installDate;
    public String remark;
    public DisMapping(){

    }

    @Override
    public String toString() {
        return "DisMapping{" +
                "disId='" + disId + '\'' +
                ", disCode='" + disCode + '\'' +
                ", disName='" + disName + '\'' +
                ", state='" + state + '\'' +
                ", installDate=" + installDate +
                ", remark='" + remark + '\'' +
                '}';
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDisId() {
        return disId;
    }

    public String getDisCode() {
        return disCode;
    }

    public String getDisName() {
        return disName;
    }

    public String getState() {
        return state;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public String getRemark() {
        return remark;
    }
}
