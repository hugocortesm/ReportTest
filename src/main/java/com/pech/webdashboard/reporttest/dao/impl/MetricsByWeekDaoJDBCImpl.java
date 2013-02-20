package com.pech.webdashboard.reporttest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pech.webdashboard.reporttest.dao.MetricsByWeekDao;
import com.pech.webdashboard.reporttest.data.ConnectionManager;
import com.pech.webdashboard.reporttest.vo.MetricByWeek;

public class MetricsByWeekDaoJDBCImpl implements MetricsByWeekDao {

	private Connection connection;

	public MetricsByWeekDaoJDBCImpl() {
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			System.out.println("Erro al obetener la conexxion JDBC dao");
		}
	}

	public List<MetricByWeek> getMetricsByDate(Date fromDate, Date toDate)
			throws SQLException {
		List<MetricByWeek> metricsByWeek = new ArrayList<MetricByWeek>();
		ResultSet result = null;
		String query = "select mbw.id, mbw.reportSuiteId, rs.name reporSuiteName, mbw.metricId, m.metricCode, mbw.metricDate, mbw.elementId, e.name elementName "
				+ " from MetricsByWeek mbw left join ReportSuites rs on mbw.reportSuiteId = rs.id"
				+ " left join Metrics m on m.id = mbw.metricId"
				+ " left join Elements e on mbw.elementId=e.id"
				+ " where mbw.metricdate between ? and ?"
				+ " and mbw.elementId=1"
				+ " order by mbw.reportSuiteId, mbw.metricId, mbw.metricDate";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setDate(1, new java.sql.Date(fromDate.getTime()));
		ps.setDate(2, new java.sql.Date(toDate.getTime()));
		result = ps.executeQuery();
		while (result.next()) {
			metricsByWeek.add(getRow(result));
		}

		return metricsByWeek;
	}

	private MetricByWeek getRow(ResultSet rs) throws SQLException {
		MetricByWeek mbw = new MetricByWeek();
		String name = null;
		String metricCode = null;
		String elementName = null;
		mbw.setId(rs.getInt("id"));
		mbw.getReportSuite().setId(rs.getInt("reportSuiteId"));
		name = rs.getString("reporSuiteName");
		mbw.getReportSuite().setName(name != null ? name : "");
		mbw.getMetric().setId(rs.getInt("metricId"));
		metricCode = rs.getString("metricCode");
		mbw.getMetric().setMetricCode(metricCode != null ? metricCode : "");
		mbw.setMetricDate(rs.getDate("metricDate"));
		mbw.getElement().setId(rs.getInt("elementId"));
		elementName = rs.getString("elementName");
		mbw.getElement().setName(elementName != null ? elementName : "");
		return mbw;
	}

}
