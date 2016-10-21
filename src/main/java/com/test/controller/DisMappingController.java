package com.test.controller;

import com.test.model.DisMapping;
import com.test.service.DisMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LPT12013 on 2016/9/12.
 */
@RestController
@RequestMapping("/dismapp")
public class DisMappingController {
    @Autowired
    private DisMappingService disMappingService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<DisMapping> disMappingList(DisMapping disMapping,
                                            HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        if(disMapping !=null) {
            params.put("disId", disMapping.getDisId());
            params.put("disCode", disMapping.getDisCode());
            params.put("disName", disMapping.getDisName());
        }
        List<DisMapping>  disMappings = disMappingService.listDisMapping(params);

       return disMappings;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addDisMapping(@RequestBody Map<String,Object> params, HttpServletRequest request, HttpServletResponse response ){
        params.put("inventoryDate","2016-09-19");
        params.put("state","3");
        disMappingService.addDisMapping(params);
    }

    @RequestMapping(value = "/{disId}", method = RequestMethod.PUT)
    public void updateDisMapping(@PathVariable String disId, @RequestBody DisMapping disMapping,
                                 HttpServletRequest request, HttpServletResponse response){

        disMappingService.updateDisMapping(null);
    }

    @RequestMapping(value = "/{disId}", method = RequestMethod.DELETE)
    public void deleteDisMapping(@PathVariable String disId){
        Map<String,Object> params = new HashMap<>();
        params.put("disId",disId);
        disMappingService.deleteDisMapping(params);
    }

}
