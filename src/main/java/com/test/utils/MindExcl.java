package com.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.test.model.MyMind;
import net.sf.mpxj.*;
import net.sf.mpxj.mspdi.MSPDIWriter;
import net.sf.mpxj.writer.ProjectWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by LPT12013 on 2016/10/18.
 */
public class MindExcl {

    private int rowCount = 0;

    private int getRowCount() {
        return rowCount;
    }

    private  void setRowCount() {
        this.rowCount ++;
    }

    public  void main(String[] args) {

//        System.out.println(testMind.getMyMindChildren(myMind,null));


    }

    public File execute(String json) throws Exception {
        MyMind myMind = convertJsonToMyMind(json);
        String reMsg=null;
        try {
            //封装Excel报表
            SXSSFWorkbook workbook = this.generateExcel(myMind);
            String sfliepath = "D:\\test";
            sfliepath += new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator;
            File parent = new File(sfliepath);// 硬盘路径

            String stime = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
            String children = myMind.getProjectName().concat(stime);
            children = children + ((int) (100 + new Random().nextInt(900)))+".xlsx";
            File desc = new File(parent, children);
            OutputStream out = null;
            try {
                parent = desc.getParentFile();
                if (!parent.exists())
                    parent.mkdirs();
                out = new FileOutputStream(desc);
                workbook.write(out);
            } catch (IOException e) {
                throw new Exception("写出错误文件失败:"+e);
            } finally {
                try {
                    if (out != null)
                        out.close();
                } catch (IOException e) {
                    throw new Exception("关闭流失败:"+e);
                }
            }
            workbook.dispose();
            String rets =desc.getAbsolutePath();
            System.out.println("硬盘路径+文件名::"+rets);

            if (StringUtils.isNotEmpty(desc.getPath())) {
                reMsg = desc.getPath();
            }
            return desc;
        } catch (Exception e) {
           throw  e;
        }

    }

    private SXSSFWorkbook generateExcel(MyMind myMind) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");

        int maxIndex = fillHeaderCell(wb, sheet,myMind);// 创建excel表头(第一行和第二行)
        if (myMind != null){
            fillDataCell(myMind,wb,sheet,maxIndex,null);
        }
        return wb;
    }

    /**
     * 创建excel表头(第一行和第二行)
     * @param wb 工作簿
     * @param sheet sheet
     */
    private int fillHeaderCell(SXSSFWorkbook wb, Sheet sheet,MyMind myMind) {
        // 第一行
        Row row = sheet.createRow(0);
        CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
        Cell cell = row.createCell(0);
        int maxIndex = getMyMindChildren(myMind,null);//最大层数
        int count = 0;
        while(count < maxIndex){
            cell = row.createCell(count);
            cell.setCellStyle(sencondRowStyle);
            sheet.setColumnWidth(count, (short) (70 * 60));// 设置当前行单元格宽度
            cell.setCellValue("资源名_"+count);
            count++;
        }

        cell = row.createCell(count);
        cell.setCellStyle(sencondRowStyle);
        sheet.setColumnWidth(count++, (short) (70 * 80));// 设置当前行单元格宽度
        cell.setCellValue("责任人");

        cell = row.createCell(count);
        cell.setCellStyle(sencondRowStyle);
        sheet.setColumnWidth(count++, (short) (70 * 80));// 设置当前行单元格宽度
        cell.setCellValue("人天");

        cell = row.createCell(count);
        cell.setCellStyle(sencondRowStyle);
        sheet.setColumnWidth(count++, (short) (70 * 80));// 设置当前行单元格宽度
        cell.setCellValue("开始时间");

        cell = row.createCell(count);
        cell.setCellStyle(sencondRowStyle);
        sheet.setColumnWidth(count++, (short) (70 * 80));// 设置当前行单元格宽度
        cell.setCellValue("结束时间");

        cell = row.createCell(count);
        cell.setCellStyle(sencondRowStyle);
        sheet.setColumnWidth(count++, (short) (70 * 80));// 设置当前行单元格宽度
        cell.setCellValue("备注");
        return maxIndex;
    }

    /**
     * 为sheet填充数据
     * @param sheet sheet
     * @param wb 工作簿
     * @param myMind
     */
    private void fillDataCell(MyMind myMind, SXSSFWorkbook wb,
                              Sheet sheet,Integer maxIndex, Integer index) {
        CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
        CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
        CellStyle numberStyle = ExcelUtils.getDefaultNumberStyle(wb);// 数量样式
        CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb,"yyyy-MM-dd");
        if(index == null ) index = 0;
        int nowIndex = maxIndex;
        //添加row
        setRowCount();
        Row row = sheet.createRow(getRowCount());
        for (int i=0;i<maxIndex;i++){
            Cell cell = row.createCell(i);
            cell.setCellStyle(strStyle);
            if(i==index)
                cell.setCellValue(myMind.getProjectName());
            else
                cell.setCellValue("");
        }
        Cell cell = row.createCell(nowIndex++);
        cell.setCellStyle(strStyle);
        cell.setCellValue(myMind.getResponsible());
        if(myMind.getPeopleDay() != null) {
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(strStyle);
            cell.setCellValue(myMind.getPeopleDay());
        }else {
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(strStyle);
            cell.setCellValue("");
        }
        if(myMind.getBeginDate() != null) {
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(myMind.getBeginDate());
        }else{
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(strStyle);
            cell.setCellValue("");
        }
        if(myMind.getEndDate() != null) {
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(myMind.getEndDate());
        }else{
            cell = row.createCell(nowIndex++);
            cell.setCellStyle(strStyle);
            cell.setCellValue("");
        }
        if(myMind !=null) {
            List<MyMind> myMinds = myMind.getChildren();
            if (myMinds != null && !myMinds.isEmpty()) {
                index++;
                for (MyMind mind : myMinds) {
                    fillDataCell(mind, wb, sheet, maxIndex, index);
                }
            }
        }

    }




    /**
     * json反序列化成对象
     * @param jsonArrStr
     * @param clazz
     * @return
     */
    public static Object getObjFromJsonArrStr(String jsonArrStr, Class clazz)
    {
        JSONObject jsonObj = JSONObject.parseObject(jsonArrStr);
        return JSONObject.toJavaObject(jsonObj, clazz);
    }

    /**
     * json转换成myMind对象
     * @param json
     * @return
     */
    public static MyMind convertJsonToMyMind(String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        json = jsonObject.getJSONObject("root").toJSONString();
        MyMind myMind = (MyMind) getObjFromJsonArrStr(json,MyMind.class);
        return myMind;
    }

    private int getMyMindChildren(MyMind myMind,Integer count){
        if(count == null) count = 0;
        count++;
        int max =0;
        int i=count;
       if(myMind !=null) {
           List<MyMind> myMinds = myMind.getChildren();
           if(myMinds !=null && !myMinds.isEmpty()){
               for (MyMind mind : myMinds ) {
                   count = getMyMindChildren(mind,i);
                   if(count > max) max =count;
               }
           }else{
               max =count;
           }

       }
        return max;
    }



    public static String json2 ="{\n" +
            "\t\"root\": {\n" +
            "\t\t\"id\": \"dysecqgp\",\n" +
            "\t\t\"text\": \"My Mind Map\",\n" +
            "\t\t\"layout\": \"map\",\n" +
            "\t\t\"peopleDay\": \"15\",\n" +
            "\t\t\"pic\": \"Dary\",\n" +
            "\t\t\"startTime\": \"2016-10-03\",\n" +
            "\t\t\"endTime\": \"2016-10-21\",\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": \"yzualnuk\",\n" +
            "\t\t\t\t\"text\": \"需求\",\n" +
            "\t\t\t\t\"side\": \"right\",\n" +
            "\t\t\t\t\"peopleDay\": \"2\",\n" +
            "\t\t\t\t\"pic\": \"zhao\",\n" +
            "\t\t\t\t\"startTime\": \"2016-10-03\",\n" +
            "\t\t\t\t\"endTime\": \"2016-10-04\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": \"kicsssoo\",\n" +
            "\t\t\t\t\"text\": \"开发\",\n" +
            "\t\t\t\t\"side\": \"left\",\n" +
            "\t\t\t\t\"peopleDay\": \"10\",\n" +
            "\t\t\t\t\"pic\": \"Dary\",\n" +
            "\t\t\t\t\"startTime\": \"2016-10-05\",\n" +
            "\t\t\t\t\"endTime\": \"2016-10-14\",\n" +
            "\t\t\t\t\"children\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"id\": \"yylhxxik\",\n" +
            "\t\t\t\t\t\t\"text\": \"首页\",\n" +
            "\t\t\t\t\t\t\"peopleDay\": \"2\",\n" +
            "\t\t\t\t\t\t\"pic\": \"dary\",\n" +
            "\t\t\t\t\t\t\"startTime\": \"2016-10-05\",\n" +
            "\t\t\t\t\t\t\"endTime\": \"2016-10-06\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"id\": \"qntgqeik\",\n" +
            "\t\t\t\t\t\t\"text\": \"baob\",\n" +
            "\t\t\t\t\t\t\"peopleDay\": \"4\",\n" +
            "\t\t\t\t\t\t\"pic\": \"dary\",\n" +
            "\t\t\t\t\t\t\"startTime\": \"2016-10-07\",\n" +
            "\t\t\t\t\t\t\"endTime\": \"2016-10-12\",\n" +
            "\t\t\t\t\t\t\"children\": [\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"id\": \"elueqjsm\",\n" +
            "\t\t\t\t\t\t\t\t\"text\": \"hah\",\n" +
            "\t\t\t\t\t\t\t\t\"peopleDay\": \"2\",\n" +
            "\t\t\t\t\t\t\t\t\"pic\": \"124\",\n" +
            "\t\t\t\t\t\t\t\t\"startTime\": \"2016-10-12\",\n" +
            "\t\t\t\t\t\t\t\t\"endTime\": \"2016-10-14\"\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\"id\": \"sotdjtoj\",\n" +
            "\t\t\t\t\t\t\t\t\"text\": \"test2\",\n" +
            "\t\t\t\t\t\t\t\t\"peopleDay\": \"2\",\n" +
            "\t\t\t\t\t\t\t\t\"pic\": \"dary\",\n" +
            "\t\t\t\t\t\t\t\t\"startTime\": \"2016-10-17\",\n" +
            "\t\t\t\t\t\t\t\t\"endTime\": \"2016-10-18\"\n" +
            "\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"id\": \"ljhxebmh\",\n" +
            "\t\t\t\t\t\t\"text\": \"ceshi\",\n" +
            "\t\t\t\t\t\t\"peopleDay\": \"4\",\n" +
            "\t\t\t\t\t\t\"pic\": \"arno\",\n" +
            "\t\t\t\t\t\t\"startTime\": \"2016-10-18\",\n" +
            "\t\t\t\t\t\t\"endTime\": \"2016-10-21\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t]\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}\n";


    public static String json3="{\n" +
            "\t\"root\": {\n" +
            "\t\t\"id\": \"dysecqgp\",\n" +
            "\t\t\"text\": \"My Mind Map\",\n" +
            "\t\t\"layout\": \"map\",\n" +
            "\t\t\"peopleDay\": \"12\",\n" +
            "\t\t\"pic\": \"Dary\",\n" +
            "\t\t\"startTime\": \"2016-10-01\",\n" +
            "\t\t\"endTime\": \"2016-10-12\",\n" +
            "\t\t\"children\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": \"yzualnuk\",\n" +
            "\t\t\t\t\"text\": \"需求\",\n" +
            "\t\t\t\t\"side\": \"right\",\n" +
            "\t\t\t\t\"peopleDay\": \"2\",\n" +
            "\t\t\t\t\"pic\": \"zhao\",\n" +
            "\t\t\t\t\"startTime\": \"2016-10-03\",\n" +
            "\t\t\t\t\"endTime\": \"2016-10-04\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": \"kicsssoo\",\n" +
            "\t\t\t\t\"text\": \"开发\",\n" +
            "\t\t\t\t\"side\": \"left\",\n" +
            "\t\t\t\t\"peopleDay\": \"10\",\n" +
            "\t\t\t\t\"pic\": \"Dary\",\n" +
            "\t\t\t\t\"startTime\": \"2016-10-03\",\n" +
            "\t\t\t\t\"endTime\": \"2016-10-14\"\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}\n";

}
