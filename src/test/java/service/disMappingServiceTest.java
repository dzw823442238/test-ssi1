package service;

import com.test.model.DisMapping;
import com.test.service.DisMappingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LPT12013 on 2016/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class disMappingServiceTest {
    @Autowired
    DisMappingService disMappingService;

    @Test
    public void list(){
        Map<String,Object> params = new HashMap<>();
        params.put("disId","XFJ001");

       List<DisMapping> list = disMappingService.listDisMapping(params);
        for (DisMapping mapping :
                list) {
            System.out.println(mapping);
        }
    }

}
