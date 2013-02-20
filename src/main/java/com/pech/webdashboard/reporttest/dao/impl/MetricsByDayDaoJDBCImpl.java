package com.pech.webdashboard.reporttest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pech.webdashboard.reporttest.dao.MetricsByDayDao;
import com.pech.webdashboard.reporttest.data.ConnectionManager;
import com.pech.webdashboard.reporttest.vo.MetricByDay;

public class MetricsByDayDaoJDBCImpl implements MetricsByDayDao {

	private Connection connection;

	public MetricsByDayDaoJDBCImpl() {
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			System.out.println("Erro al obetener la conexxion JDBC dao");
		}
	}

	public List<MetricByDay> getMetricsByDate(Date fromDate, Date toDate) throws SQLException {
		List<MetricByDay> metricsbyDay = new ArrayList<MetricByDay>();
		ResultSet result = null;
		String query = "select mbd.id, mbd.reportSuiteId, rs.name reporSuiteName, mbd.metricId, m.metricCode, mbd.metricDate, mbd.elementId, e.name elementName "
				+ " from MetricsByDay mbd left join ReportSuites rs on mbd.reportSuiteId = rs.id "
				+ " left join Metrics m on mbd.metricId=m.id "
				+ " left join Elements e on mbd.elementId=e.id "
				+ " where mbd.metricdate between ? and ? "
				+ " order by mbd.reportSuiteId, mbd.metricId, mbd.metricDate";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(fromDate.getTime()));
			ps.setDate(2, new java.sql.Date(toDate.getTime()));
			result = ps.executeQuery();
			while (result.next()) {
				metricsbyDay.add(getRow(result));
			}
		} catch (SQLException sqle) {
			System.out.println("Error consultando la tabla metricsbyday");
			sqle.printStackTrace();
			throw sqle;
		}
		return metricsbyDay;
	}

	private MetricByDay getRow(ResultSet rs) throws SQLException {
		MetricByDay mbd = new MetricByDay();
		String name = null;
		String metricCode = null;
		String elementName = null;
		mbd.setId(rs.getInt("id"));
		mbd.getReportSuite().setId(rs.getInt("reportSuiteId"));
		name = rs.getString("reporSuiteName");
		mbd.getReportSuite().setName(name != null ? name : "");
		mbd.getMetric().setId(rs.getInt("metricId"));
		metricCode = rs.getString("metricCode");
		mbd.getMetric().setMetricCode(metricCode != null ? metricCode : "");
		mbd.setMetricDate(rs.getDate("metricDate"));
		mbd.getElement().setId(rs.getInt("elementId"));
		elementName = rs.getString("elementName");
		mbd.getElement().setName(elementName != null ? elementName : "");
		return mbd;
	}

}
