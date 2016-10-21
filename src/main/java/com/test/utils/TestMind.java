package com.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.test.model.MyMind;
import net.sf.mpxj.*;
import net.sf.mpxj.Resource;
import net.sf.mpxj.Task;
import net.sf.mpxj.mspdi.MSPDIReader;
import net.sf.mpxj.mspdi.MSPDIWriter;
import net.sf.mpxj.reader.ProjectReader;
import net.sf.mpxj.writer.ProjectWriter;


import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by LPT12013 on 2016/10/18.
 */
public class TestMind {

    private int count = 0;

    private int getCount() {
        return count;
    }

    private synchronized void setCount() {
        this.count ++;
    }

    public static void main(String[] args) throws MPXJException {
        MyMind myMind = convertJsonToMyMind(json2);
        TestMind testMind = new TestMind();
        ProjectWriter writer = new MSPDIWriter();
        ProjectReader projectReader = new MSPDIReader();
        File file =new File("d:\\model.xml");
        ProjectFile projectFile = projectReader.read(file);
//        ProjectFile projectFile = testMind.getProjectFile(myMind);
        testMind.getTask(projectFile);
        try {
            writer.write(projectFile, "d:\\test2.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getTask(ProjectFile projectFile ){
        System.out.println("---------task--------");
        Iterator<Task> iterator =projectFile.getAllTasks().iterator();
        while (iterator.hasNext()){
           Task task = iterator.next();

        }

    }

    public  ProjectFile getProjectFile(MyMind myMind){
        ProjectFile project = new ProjectFile();
        setChildrenTask(project, myMind ,null,null);

        Resource r1 = project.addResource();
        r1.setName("one");
        r1.setPercentWorkComplete(ResourceAssignment.DEFAULT_UNITS);

        Resource r2 = project.addResource();
        r2.setName("two");
        r2.setPercentWorkComplete(ResourceAssignment.DEFAULT_UNITS);

        return project;
    }

    /**
     * 遍历myMind及其子对象，转换为Task ,
     * @param project
     * @param myMind
     * @param parentTask
     * @return
     */
    private void setChildrenTask(ProjectFile project, MyMind myMind,Task parentTask,Integer index){
        Task task = null;
        //没有父节点：在ProjectFile上创建Task
        if(parentTask == null) {
            index = 0;
            task = project.addTask();
//            task.setWBS("0");
        }else
            task = project.addTask();
        //设置Task属性
//        setCount();
//        task.setUniqueID(getCount());
//        task.setID(getCount());
//        if(parentTask !=null) {
//            parentTask.addChildTask(task);
//        }
        index++;

        task.setOutlineLevel(index);
        task.setName(myMind.getProjectName());
        task.setStart(myMind.getBeginDate());
        task.setFinish(myMind.getEndDate());
        task.setDuration(Duration.getInstance(myMind.getPeopleDay(), TimeUnit.DAYS)); //工期
        task.setNotes(myMind.getRemarks());//备注
//        if(parentTask != null) {
//            task.addPredecessor(parentTask, RelationType.FINISH_FINISH, null);
//        }

        Resource resource = null;
        //获取子节点，有：递归遍历子节点, 没有：设置责任人
        List<MyMind> myMinds = myMind.getChildren();
        if(myMinds != null && !myMinds.isEmpty() ){

            for (MyMind mind : myMinds) {
                setChildrenTask(project, mind, task,index);
            }
        }
        else {
            ListIterator<Resource> resourceList =project.getAllResources().listIterator();
            boolean status = true;
            while (resourceList.hasNext()){
                resource = resourceList.next();
                if ( (resource.getName()).equals( myMind.getResponsible()) ){
                    status = false;
                    break;
                }
            }

//            resourceList.forEachRemaining(resource ->  resource.getName().equals(myMind.getResponsible()));
            if(status) {
                resource = project.addResource();

                resource.setName(myMind.getResponsible());
                resource.setPercentWorkComplete(ResourceAssignment.DEFAULT_UNITS);
            }
            // ResourceAssignment 为资源对应任务关系。
            ResourceAssignment a1 = task.addResourceAssignment(resource);
            a1.setRemainingWork(Duration.getInstance(myMind.getPeopleDay() * 8, TimeUnit.HOURS));
            a1.setWork(Duration.getInstance(myMind.getPeopleDay() * 8, TimeUnit.HOURS));
            a1.setStart(myMind.getBeginDate());
            a1.setFinish(myMind.getEndDate());
            a1.setUnits(ResourceAssignment.DEFAULT_UNITS);
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
