package com.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.test.dao.MyMindDao;
import com.test.model.MyMind;
import com.test.service.MyMindService;
import com.test.utils.MindExcl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dary.Deng on 2016/10/17.
 */
@Service("myMindService")
public class MyMindServiceImpl implements MyMindService {

    @Autowired
    private MyMindDao myMindDao;

    @Override
    @Transactional(readOnly = true)
    public List<MyMind> listMyMind(Map<String, Object> params) {
        return myMindDao.listMyMind(params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMyMind(MyMind myMind) {
        myMindDao.addMyMind(myMind);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMyMind(MyMind myMind) {
        myMindDao.updateMyMind(myMind);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMyMind(MyMind myMind) {
        myMindDao.deleteMyMind(myMind);
    }

    @Override
    public void saveMyMind(String json) throws Exception{
        MyMind myMind = resolveJsonToMyMind(json);
        Map<String,Object> params = new HashMap<>();
        params.put("id",myMind.getId());
        List<MyMind> myMinds = listMyMind(params);
        //id 不存在：新增，存在：修改
        if(myMinds == null || myMinds.isEmpty()) {
            myMind.setCreated(new Date());
            addMyMind(myMind);
        }else{
            myMind.setUpdated(new Date());
            updateMyMind(myMind);
        }

    }

    /**
     * 下载文件
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public File download(String id) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        List<MyMind> myMinds = listMyMind(params);
        MyMind myMind = null;
        if(myMinds != null && !myMinds.isEmpty()) {
            myMind = myMinds.get(0);
            MindExcl mindExcl = new MindExcl();
            //生成文件
            File file = mindExcl.execute(myMind.getMindJson());
            return file;
        }
       return null;

    }



    /**
     * 解析json根节点root对象的属性,返回 mymind 对象
     * @param json
     * @return MyMind
     */
    private MyMind resolveJsonToMyMind(String json){
        MyMind myMind = new MyMind();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject root = jsonObject.getJSONObject("root");
//        String mindId = root.getString("id");
        myMind.setId(root.getString("id"));
        myMind.setProjectName(root.getString("text"));
        myMind.setBeginDate(root.getDate("startTime"));
        myMind.setResponsible(root.getString("pic"));
        myMind.setEndDate(root.getDate("endTime"));
        myMind.setPeopleDay(root.getInteger("peopleDay"));
        myMind.setRemarks(root.getString("remarks"));
        myMind.setMindJson(json);

        return  myMind;
    }
}
