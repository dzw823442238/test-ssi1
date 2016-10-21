package com.test.utils;

import net.sf.mpxj.*;
import net.sf.mpxj.mspdi.MSPDIWriter;
import net.sf.mpxj.writer.ProjectWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by LPT12013 on 2016/10/18.
 */
public class Test {

    public static void main(String[] args)  throws  Exception{
        ProjectWriter writer = new MSPDIWriter();
        ProjectFile file  = new ProjectFile();
        Task task1 = file.addTask();
        task1.setName("task1");
        task1.setUniqueID(1);
        task1.setID(1);
        int a = 0;
        if (null != task1.getOutlineLevel()) {
            a = task1.getOutlineLevel();
        }
        task1.setOutlineLevel(a+1);
        Task task11 = task1.addTask();
        task11.setName("task1-1");
        task11.setDuration(Duration.getInstance(6, TimeUnit.DAYS));
        task11.setStart(java.sql.Date.valueOf("2008-8-21"));
        task11.setFinish(java.sql.Date.valueOf("2008-8-26"));
//        task11.setPercentageComplete(NumberUtility.getDouble(90D));
        task11.setUniqueID(2);
        task11.setID(2);
        task11.setResourceNames("one");
        Task task12 = task1.addTask();
        task12.setName("task12");
        task12.setDuration(Duration.getInstance(5, TimeUnit.DAYS));
        task12.setStart(java.sql.Date.valueOf("2008-8-27"));
        task12.setFinish(java.sql.Date.valueOf("2008-8-31"));
        task12.setResourceNames("two");
        task12.setUniqueID(3);
        task12.setID(3);
        Relation r=task12.addPredecessor(task11,RelationType.FINISH_START,Duration.getInstance(6, TimeUnit.DAYS));
//        r.setType(RelationType.FINISH_START);
//        r.setTaskUniqueID(task11.getUniqueID());
        ProjectCalendar pc=file.addDefaultBaseCalendar();
        pc.setName("压榨民工");
        pc.setWorkingDay(Day.SUNDAY, true);
        pc.setWorkingDay(Day.MONDAY, true);
        pc.setWorkingDay(Day.TUESDAY, true);
        pc.setWorkingDay(Day.WEDNESDAY, true);
        pc.setWorkingDay(Day.THURSDAY, true);
        pc.setWorkingDay(Day.FRIDAY, true);
        pc.setWorkingDay(Day.SATURDAY, true);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH").parse("2008-01-01 12");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH").parse("2017-01-01 18");
        ProjectCalendarHours h1=pc.addCalendarHours(Day.SUNDAY);
        h1.addRange(new DateRange(date1,date2));
        h1.addRange(new DateRange(date1,date2));
//        h1.setRange(1, ProjectCalendar.DEFAULT_WORKING_AFTERNOON);
//        h1.addDateRange(new DateRange (ProjectCalendar.DEFAULT_START1, ProjectCalendar.DEFAULT_END1));
//        h1.addDateRange(new DateRange (ProjectCalendar.DEFAULT_START2, ProjectCalendar.DEFAULT_END2));
        ProjectCalendarHours h2=pc.addCalendarHours(Day.MONDAY);
        h2.addRange(new DateRange(date1,date2));
        h2.addRange(new DateRange(date1,date2));
        ProjectCalendarHours h3=pc.addCalendarHours(Day.TUESDAY);
        h3.addRange(new DateRange(date1,date2));
        h3.addRange(new DateRange(date1,date2));
        ProjectCalendarHours h4=pc.addCalendarHours(Day.WEDNESDAY);
        h4.addRange(new DateRange(date1,date2));
        h4.addRange(new DateRange(date1,date2));
        ProjectCalendarHours h5=pc.addCalendarHours(Day.THURSDAY);
        h5.addRange(new DateRange(date1,date2));
        h5.addRange(new DateRange(date1,date2));
        ProjectCalendarHours h6=pc.addCalendarHours(Day.FRIDAY);
        h6.addRange(new DateRange(date1,date2));
        h6.addRange(new DateRange(date1,date2));
        ProjectCalendarHours h7=pc.addCalendarHours(Day.SATURDAY);
        h7.addRange(new DateRange(date1,date2));
        h7.addRange(new DateRange(date1,date2));

        try {
            writer.write(file, "d:\\test.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
