package com.pech.webdashboard.reporttest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;

import com.pech.webdashboard.reporttest.bo.MetricsByDayBo;
import com.pech.webdashboard.reporttest.bo.MetricsByWeekBo;
import com.pech.webdashboard.reporttest.bo.impl.MetricsByDayBoDefaultImpl;
import com.pech.webdashboard.reporttest.bo.impl.MetricsByWeekBoDefaultImpl;
import com.pech.webdashboard.reporttest.data.ConnectionManager;

public class ReportApp {

	public static void main(String[] args) {
		// generateMetricByDayReport();
		generateMetricByWeekReport();
	}

	private static void generateMetricByDayReport() {
		MetricsByDayBo metricsByDayBo = new MetricsByDayBoDefaultImpl();
		Calendar fromCalByDay = Calendar.getInstance();
		Calendar toCalByDay = Calendar.getInstance();
		fromCalByDay.set(2012, Calendar.DECEMBER, 01);
		toCalByDay.set(2012, Calendar.DECEMBER, 15);

		try {
			metricsByDayBo.generateReport(fromCalByDay, toCalByDay,
					"c:\\metricsByDay.xls");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
	}

	private static void generateMetricByWeekReport() {
		MetricsByWeekBo metricsByWeekBo = new MetricsByWeekBoDefaultImpl();

		Calendar fromCalByDay = Calendar.getInstance(Locale.US);
		Calendar toCalByDay = Calendar.getInstance(Locale.US);
		fromCalByDay.set(2012, Calendar.JANUARY, 01);
		toCalByDay.set(2012, Calendar.JUNE, 11);

		Integer month = fromCalByDay.get(Calendar.MONTH);
		Integer diference = fromCalByDay.getFirstDayOfWeek()
				- fromCalByDay.get(Calendar.DAY_OF_WEEK);
		fromCalByDay.add(Calendar.DATE, diference);

		if (fromCalByDay.get(Calendar.MONTH) < month) {
			fromCalByDay.add(Calendar.DATE, 7);
		}

		month = toCalByDay.get(Calendar.MONTH);
		toCalByDay.add(Calendar.DATE, toCalByDay.getFirstDayOfWeek()
				- toCalByDay.get(Calendar.DAY_OF_WEEK));

		try {
			metricsByWeekBo.generateReport(fromCalByDay, toCalByDay,
					"c:\\metricsByWeek.xls");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection();
		}
	}

}
