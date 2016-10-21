package com.test.service.impl;

import com.test.dao.DisMappingDao;
import com.test.model.DisMapping;
import com.test.service.DisMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by LPT12013 on 2016/9/9.
 */
@Service("disMappingService")
public class DisMappingServiceImpl implements DisMappingService {
    @Autowired
    private DisMappingDao disMappingDao;
    @Override
    public List<DisMapping> listDisMapping(Map<String, Object> params) {
        return disMappingDao.listDisMapping(params);
    }

    @Override
    public void addDisMapping(Map<String, Object> params) {
        disMappingDao.insertDisMapping(params);
    }

    @Override
    public void updateDisMapping(Map<String, Object> params) {
        disMappingDao.updateDisMapping(params);
    }

    @Override
    public void deleteDisMapping(Map<String, Object> params) {
        disMappingDao.deleteDisMapping(params);
    }
}
