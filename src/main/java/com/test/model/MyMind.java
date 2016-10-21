package com.test.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Date;

/**
 * My Mind
 * Created by Dary.Deng on 2016/10/17.
 */
public class MyMind {

    private String id;
    @JSONField(name = "text")
    private String projectName; //项目名
    @JSONField(name = "pic")
    private String responsible; //责任人
    private Integer peopleDay; //人天
    @JSONField(name = "startTime")
    private Date beginDate; // 开始时间
    @JSONField(name = "endTime")
    private Date endDate; // 结束时间
    private String remarks; // 备注
    private String mindJson; // my mind json串
    private Date created;
    private String createby;
    private Date updated;
    private String updateby;

    private ArrayList<MyMind> children;
    public MyMind() {

    }

    @Override
    public String toString() {
        return "MyMind{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", responsible='" + responsible + '\'' +
                ", peopleDay='" + peopleDay + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", remarks='" + remarks + '\'' +
                ", mindJson='" + mindJson + '\'' +
                ", created=" + created +
                ", createby='" + createby + '\'' +
                ", updated=" + updated +
                ", updateby='" + updateby + '\'' +
                ", children=" + children +
                '}';
    }

    public ArrayList<MyMind> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MyMind> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Integer getPeopleDay() {
        return peopleDay;
    }

    public void setPeopleDay(Integer peopleDay) {
        this.peopleDay = peopleDay;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMindJson() {
        return mindJson;
    }

    public void setMindJson(String mindJson) {
        this.mindJson = mindJson;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }
}
