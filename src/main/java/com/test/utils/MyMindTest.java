package com.test.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Date;

/**
 * My Mind
 * Created by Dary.Deng on 2016/10/17.
 */
public class MyMindTest {

    private String id;
    private String text; //项目名
    @JSONField(name = "pic")
    private String responsible; //责任人
    private String peopleDay; //人天
    private Date beginDate; // 开始时间
    private Date endDate; // 结束时间
    private String remarks; // 备注
    private String mindJson; // my mind json串
    private Date created;
    private String createby;
    private Date updated;
    private String updateby;

    private ArrayList<MyMindTest> children;

    @Override
    public String toString() {
        return "MyMind{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", pic='" + responsible + '\'' +
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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponsible() {
        return responsible;
    }

    @JSONField(name = "pic")
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getPeopleDay() {
        return peopleDay;
    }

    public void setPeopleDay(String peopleDay) {
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
