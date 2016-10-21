package com.test.dao;

import com.test.model.DisMapping;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by LPT12013 on 2016/9/9.
 */
@Repository
public interface DisMappingDao {
    public List<DisMapping> listDisMapping(Map<String,Object> params);

    public void insertDisMapping(Map<String,Object> params);

    public void updateDisMapping(Map<String,Object> params);

    public void deleteDisMapping(Map<String,Object> params);
}
