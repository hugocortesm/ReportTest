package com.pech.webdashboard.reporttest.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.pech.webdashboard.reporttest.vo.MetricByWeek;

public interface MetricsByWeekDao {

	List<MetricByWeek> getMetricsByDate(Date fromDate, Date toDate)
			throws SQLException;
}
