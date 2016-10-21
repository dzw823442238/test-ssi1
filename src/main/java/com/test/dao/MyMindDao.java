package com.test.dao;

import com.test.model.MyMind;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Dary.Deng on 2016/10/17.
 */
@Repository
public interface MyMindDao {
    public List<MyMind> listMyMind(Map<String, Object> params);

    public void addMyMind(MyMind myMind);

    public void updateMyMind(MyMind myMind);

    public void deleteMyMind(MyMind myMind);
}
