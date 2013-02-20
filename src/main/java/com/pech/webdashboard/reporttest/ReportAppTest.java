package com.pech.webdashboard.reporttest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pech.webdashboard.reporttest.data.ConnectionManager;

public class ReportAppTest {

	public static void main(String[] args) {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"ApplicationContext.xml");
			ReportGenerator reportGenerator = (ReportGenerator) ctx
					.getBean("reportGenerator");

			Calendar fromCalByDay = Calendar.getInstance();
			Calendar toCalByDay = Calendar.getInstance();
			fromCalByDay.set(2012, Calendar.DECEMBER, 01);
			toCalByDay.set(2012, Calendar.DECEMBER, 15);

			reportGenerator.generateMetricByDayReport(fromCalByDay, toCalByDay,
					"c:\\metricsByDay.xls");

			Calendar fromCalByWeek = Calendar.getInstance(Locale.US);
			Calendar toCalByWeek = Calendar.getInstance(Locale.US);
			fromCalByWeek.set(2012, Calendar.JANUARY, 01);
			toCalByWeek.set(2012, Calendar.JUNE, 11);

			reportGenerator.generateMetricByWeekReport(fromCalByWeek,
					toCalByWeek, "c:\\metricsByWeek.xls");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
	}
}
