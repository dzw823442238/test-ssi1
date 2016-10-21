package demo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by LPT12013 on 2016/10/11.
 */
public class Test {

    public static void main(String[] args) {
        String json = "[{\"name\":\"key\",\"value\":\"大小\"},{\"name\":\"value\",\"value\":\"大,中,小\"}," +
                "{\"name\":\"key\",\"value\":\"颜色\"},{\"name\":\"value\",\"value\":\"红,黄,蓝,绿\"}]";
//        JSONArray json = JSONArray.fromObject(json);

        json = "{\"root\": {\"id\": \"124\",\"text\": \"Project Name\",\"layout\": \"map\"}}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        jsonObject.getJSONObject("root").getString("id");

    }
}
