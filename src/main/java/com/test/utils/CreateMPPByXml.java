package com.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.mpxj.*;
import net.sf.mpxj.mspdi.MSPDIWriter;
import net.sf.mpxj.writer.ProjectWriter;

public class CreateMPPByXml {
	public CreateMPPByXml() {
	}

	public static void main(String args[]) {
		try {
			create("d:\\output.xml");
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	private static void create(String filename) throws Exception {
		ProjectFile file = new ProjectFile();

//		file.setAutoResourceID(true);
//		file.setAutoResourceUniqueID(true);

		Resource r1 = file.addResource();
		r1.setName("one");
		r1.setPercentWorkComplete(ResourceAssignment.DEFAULT_UNITS);

		Resource r2 = file.addResource();
		r2.setName("two");
		r2.setPercentWorkComplete(ResourceAssignment.DEFAULT_UNITS);

		Task task1 = file.addTask();
		task1.setName("task1");
		task1.setUniqueID(1);
		task1.setID(1);
		task1.setOutlineLevel(new Integer(1));
		task1.setDuration(Duration.getInstance(14, TimeUnit.DAYS));
		task1.setOutlineNumber("1");

		Task task11 = file.addTask();
		task11.setName("task1-1");
//		task11.setDuration(Duration.getInstance(3, TimeUnit.DAYS));
//		task11.setStart(changeType("2016-10-1 8:00:00"));
//		task11.setFinish(changeType("2016-10-3 17:00:00"));
		task11.setUniqueID(2);
		task11.setID(2);
		task11.setOutlineLevel(new Integer(2));
		task11.setOutlineNumber("1.1");

		Task task12 = file.addTask();
		task12.setName("task1-2");
		task12.setDuration(Duration.getInstance(7, TimeUnit.DAYS));
		task12.setStart(changeType("2016-10-6 8:00:00"));
		task12.setFinish(changeType("2016-10-20 17:00:00"));
		task12.setUniqueID(4);
		task12.setID(4);
		task12.setOutlineLevel(new Integer(2));
		task12.setOutlineNumber("1.2");


		Task task111 = file.addTask();
		task111.setName("task11-1");
		task111.setDuration(Duration.getInstance(7, TimeUnit.DAYS));
		task111.setStart(changeType("2016-10-3 8:00:00"));
		task111.setFinish(changeType("2016-10-6 17:00:00"));
		task111.setUniqueID(3);
		task111.setID(3);
		task111.setOutlineLevel(new Integer(3));
		task111.setOutlineNumber("1.1.1");

//		Task task112 = file.addTask();
//		task112.setName("task11-1");
//		task112.setDuration(Duration.getInstance(7, TimeUnit.DAYS));
//		task112.setStart(changeType("2016-10-13 8:00:00"));
//		task112.setFinish(changeType("2016-10-20 17:00:00"));
//		task112.setUniqueID(4);
//		task112.setID(4);
//		task112.setOutlineLevel(new Integer(3));
//		task112.setOutlineNumber("1.1.2");

		// ResourceAssignment 为资源对应任务关系。
		ResourceAssignment a1 = task111.addResourceAssignment(r1);
		a1.setRemainingWork(Duration.getInstance(4 * 8, TimeUnit.HOURS));
		a1.setWork(Duration.getInstance(4 * 8, TimeUnit.HOURS));
		a1.setStart(changeType("2016-10-3 8:00:00"));
		a1.setFinish(changeType("2016-10-6 17:00:00"));
		a1.setUnits(ResourceAssignment.DEFAULT_UNITS);
//		a1 = task112.addResourceAssignment(r1);
//		a1.setRemainingWork(Duration.getInstance(6 * 8, TimeUnit.HOURS));
//		a1.setWork(Duration.getInstance(6 * 8, TimeUnit.HOURS));
//		a1.setStart(changeType("2016-10-13 8:00:00"));
//		a1.setFinish(changeType("2016-10-20 17:00:00"));
//		a1.setUnits(ResourceAssignment.DEFAULT_UNITS);

		ResourceAssignment a2 = task12.addResourceAssignment(r2);
		a2.setRemainingWork(Duration.getInstance(7 * 8, TimeUnit.HOURS));
		a2.setWork(Duration.getInstance(7 * 8, TimeUnit.HOURS));
		a2.setStart(changeType("2016-10-6 8:00:00"));
		a2.setFinish(changeType("2016-10-20 17:00:00"));
		a2.setUnits(ResourceAssignment.DEFAULT_UNITS);

		List timephasedPlannedt = new ArrayList();
//
//		TimephasedResourceAssignment tpras1 = new TimephasedResourceAssignment();
//		tpras1.setStart(changeType("2016-10-6 8:00:00"));
//		tpras1.setModified(false);
//		tpras1.setFinish(changeType("2016-10-8 17:00:00"));
//		tpras1.setTotalWork(Duration.getInstance(1440, TimeUnit.HOURS));
//		tpras1.setWorkPerDay(Duration.getInstance(480, TimeUnit.HOURS));
//		timephasedPlannedt.add(tpras1);
//
//		TimephasedResourceAssignment tpras2 = new TimephasedResourceAssignment();
//		tpras2.setStart(changeType("2016-10-9 8:00:00"));
//		tpras2.setModified(false);
//		tpras2.setFinish(changeType("2016-10-14 17:00:00"));
//		tpras2.setTotalWork(Duration.getInstance(0, TimeUnit.HOURS));
//		tpras2.setWorkPerDay(Duration.getInstance(0, TimeUnit.HOURS));
//		timephasedPlannedt.add(tpras2);
//
//		TimephasedResourceAssignment tpras3 = new TimephasedResourceAssignment();
//		tpras3.setStart(changeType("2016-10-15 8:00:00"));
//		tpras3.setModified(false);
//		tpras3.setFinish(changeType("2016-10-20 17:00:00"));
//		tpras3.setTotalWork(Duration.getInstance(1920, TimeUnit.HOURS));
//		tpras3.setWorkPerDay(Duration.getInstance(480, TimeUnit.HOURS));
//		timephasedPlannedt.add(tpras3);
//
//		TimephasedResourceAssignmentNormaliser normaliser = new MPPTimephasedResourceAssignmentNormaliser();
//		a2.setTimephasedNormaliser(normaliser);
//		a2.setTimephasedPlanned(timephasedPlannedt, true);
//		a2.setTimephasedComplete(new ArrayList(), false);

		// 前置关系
//		Relation r = task112.addPredecessor(task111,RelationType.FINISH_START,null);

//		r.setType(RelationType.FINISH_START);
//		r.setTaskUniqueID(task11.getUniqueID());

//		ProjectCalendar pc = file.addDefaultBaseCalendar();
//
//		pc.setUniqueID(new Integer(1));
//
//		// 设置工作日历 这里设的周1－7全部工作，如不设默认为5天工作制
//		pc.setName("标准");
//		pc.setWorkingDay(Day.SUNDAY, false);
//		pc.setWorkingDay(Day.MONDAY, true);
//		pc.setWorkingDay(Day.TUESDAY, true);
//		pc.setWorkingDay(Day.WEDNESDAY, true);
//		pc.setWorkingDay(Day.THURSDAY, true);
//		pc.setWorkingDay(Day.FRIDAY, true);
//		pc.setWorkingDay(Day.SATURDAY, false);
//
//		ProjectCalendarHours h1 = pc.addCalendarHours(Day.SUNDAY);
//		h1.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h1.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h2 = pc.addCalendarHours(Day.MONDAY);
//		h2.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h2.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h3 = pc.addCalendarHours(Day.TUESDAY);
//		h3.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h3.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h4 = pc.addCalendarHours(Day.WEDNESDAY);
//		h4.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h4.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h5 = pc.addCalendarHours(Day.THURSDAY);
//		h5.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h5.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h6 = pc.addCalendarHours(Day.FRIDAY);
//		h6.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h6.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//
//		ProjectCalendarHours h7 = pc.addCalendarHours(Day.SATURDAY);
//		h7.addRange(new DateRange(ProjectCalendar.DEFAULT_START1,
//				ProjectCalendar.DEFAULT_END1));
//		h7.addRange(new DateRange(ProjectCalendar.DEFAULT_START2,
//				ProjectCalendar.DEFAULT_END2));
//		// 设置特殊工作日。
//		ProjectCalendarException pce = pc.addCalendarException();
//		pce.setFromDate(changeType("2008-8-28 8:00:00"));
//		pce.setToDate(changeType("2008-8-29 17:00:00"));
//		pce.setWorking(true);
//		// DateRange 为工作时段。
//		DateRange dr = new DateRange(changeType("2008-8-21 8:00:00"),
//				changeType("2008-8-21 12:00:00"));
//		// 可以添加多个工作时段。
//		pce.addRange(dr);
//
//		// 设置项目默认使用的日历为新建的日历。
//		ProjectHeader ph = file.getProjectHeader();
//		ph.setCalendarName(pc.getName());

		ProjectWriter writer = new MSPDIWriter();
		writer.write(file, filename);
	}

	private static java.util.Date changeType(String date) {
		java.util.Date uDate = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (date != null && !"".equals(date)) {
				uDate = df.parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return uDate;
	}
}