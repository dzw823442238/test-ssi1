package com.test.service;

import com.test.model.DisMapping;
import com.test.model.MyMind;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Dary.Deng on 2016/10/9.
 */
public interface MyMindService {

    public List<MyMind> listMyMind(Map<String, Object> params);

    public void addMyMind(MyMind myMind);

    public void updateMyMind(MyMind myMind);

    public void deleteMyMind(MyMind myMind);

    public void saveMyMind(String json) throws Exception;

    /**
     * 下载文件
     * @param id
     * @return
     * @throws Exception
     */
    public File download(String id) throws Exception;
}
