package com.pech.webdashboard.reporttest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import com.pech.webdashboard.reporttest.bo.MetricsByDayBo;
import com.pech.webdashboard.reporttest.bo.MetricsByWeekBo;

public class ReportGenerator {

	private MetricsByDayBo metricsByDayBo;
	private MetricsByWeekBo metricsByWeekBo;

	public ReportGenerator(MetricsByDayBo metricsByDayBo,
			MetricsByWeekBo metricsByWeekBo) {
		super();
		this.metricsByDayBo = metricsByDayBo;
		this.metricsByWeekBo = metricsByWeekBo;
	}

	public void generateMetricByDayReport(Calendar fromCal, Calendar toCal,
			String file) throws IOException, SQLException {
		metricsByDayBo.generateReport(fromCal, toCal, file);
	}

	public void generateMetricByWeekReport(Calendar fromCal, Calendar toCal,
			String file) throws IOException, SQLException {

		Integer month = fromCal.get(Calendar.MONTH);
		Integer diference = fromCal.getFirstDayOfWeek()
				- fromCal.get(Calendar.DAY_OF_WEEK);
		fromCal.add(Calendar.DATE, diference);
		if (fromCal.get(Calendar.MONTH) < month) {
			fromCal.add(Calendar.DATE, 7);
		}

		toCal.add(Calendar.DATE,
				toCal.getFirstDayOfWeek() - toCal.get(Calendar.DAY_OF_WEEK));

		metricsByWeekBo.generateReport(fromCal, toCal, file);
	}

}
