package com.test.service;

import com.test.model.DisMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by LPT12013 on 2016/9/9.
 */
public interface DisMappingService {

    public List<DisMapping> listDisMapping(Map<String,Object> params);

    public void addDisMapping(Map<String,Object> params);

    public void updateDisMapping(Map<String,Object> params);

    public void deleteDisMapping(Map<String,Object> params);
}
