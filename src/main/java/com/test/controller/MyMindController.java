package com.test.controller;


import com.test.model.MyMind;
import com.test.service.MyMindService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Dary.Deng on 2016/10/13.
 */
@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping("/mind/")
public class MyMindController {
    private static Logger log = Logger.getLogger(MyMindController.class);
    @Autowired
    private MyMindService myMindService;
//    @RequestMapping(value = "/myMind/{id}",method = RequestMethod.OPTIONS)
//    public void myMind(@PathVariable String id,
//                       HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
//        response.setHeader("Access-Control-Allow-Headers","content-type");
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:63342");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        System.out.println("OPTIONS请求："+id);
//
//    }

//    @RequestMapping(value = "/myMind/*",method = RequestMethod.POST)
//    public void addMyMind(String json,
//                          HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Headers","Content-Type,*");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:63342");
//        System.out.println("myMind上传成功:"+json);
//    }

    /**
     * 保存Mind ,存在就修改，不存在新增
     *
     * @param id
     * @param json
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String saveMyMind(@PathVariable String id, @RequestBody String json,
                             HttpServletRequest request, HttpServletResponse response) {
        String responseMsg = "";
        try {
            myMindService.saveMyMind(json);
            log.info("myMind保存成功 :" + json);
        } catch (Exception e) {
            response.setStatus(400);
            responseMsg = "参数错误，解析失败";
            log.info(e);
        }

        return responseMsg;
    }

//    @RequestMapping(value = "/myMind",method = RequestMethod.DELETE)
//    public void deleteMyMind(String mindId,
//                             HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Headers","Content-Type,*");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:63342");
//        System.out.println("myMind修改成功:"+mindId);
//    }

    /**
     * 返回模板或已存在的Mind数据
     *
     * @param id       mindID
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String ModelByMyMind(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        log.info("myMind查询,id:" + id);
        String json = "";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<MyMind> myMinds = myMindService.listMyMind(params);
        //list为空：初始化 Mind json,不为空：取对象的json
        if (myMinds == null || myMinds.isEmpty()) {
            json = "{\"root\": {\"id\": \"" + UUID.randomUUID().toString()
                    + "\",\"text\": \"Project Name\",\"layout\": \"map\"}}";
        } else {
            json = myMinds.get(0).getMindJson();
        }
        return json;

    }

    @RequestMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        File file = myMindService.download(id);
        String fileName = file.getName();
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}