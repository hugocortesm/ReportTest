package com.pech.webdashboard.reporttest.bo.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pech.webdashboard.reporttest.bo.MetricsByWeekBo;
import com.pech.webdashboard.reporttest.dao.MetricsByWeekDao;
import com.pech.webdashboard.reporttest.dao.impl.MetricsByWeekDaoJDBCImpl;
import com.pech.webdashboard.reporttest.vo.Metric;
import com.pech.webdashboard.reporttest.vo.MetricByWeek;

public class MetricsByWeekBoDefaultImpl implements MetricsByWeekBo {

	private MetricsByWeekDao metricsByWeekDao;

	public MetricsByWeekBoDefaultImpl() {
		metricsByWeekDao = new MetricsByWeekDaoJDBCImpl();
	}

	public void generateReport(Calendar fromCal, Calendar toCal, String file)
			throws IOException, SQLException {
		Long processStartTime = new Date().getTime();
		List<MetricByWeek> metricsByWeek = null;
		try {
			metricsByWeek = metricsByWeekDao.getMetricsByDate(
					fromCal.getTime(), toCal.getTime());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		}
		Long processFinishTime = new Date().getTime();
		System.out.println("Tiempo para realizar la consulta: "
				+ ((processFinishTime - processStartTime) / 1000)
				+ " segundos. Numero de registros: " + metricsByWeek.size());

		System.out.println("Generando archivo. " + new Date());

		processStartTime = new Date().getTime();
		try {
			generateReport(fromCal, toCal, metricsByWeek, file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		processFinishTime = new Date().getTime();
		System.out.println("Tiempo para generar el archivo: "
				+ ((processFinishTime - processStartTime) / 1000)
				+ " segundos: " + new Date());
		System.out.println("Reprot MetricsByWeek Complete");
	}

	private void generateReport(Calendar fromDate, Calendar toDate,
			List<MetricByWeek> metricsByWeek, String file) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			StringBuilder sb = new StringBuilder();
			fw.append("<table>");
			Integer currentMetricId = 0;
			Integer currentReportSuiteId = 0;
			Set<Integer> processedMetrics = new HashSet<Integer>();
			Calendar currentDate = (Calendar) fromDate.clone();
			Calendar metricDate = Calendar.getInstance();
			fw.append(generateHeader((Calendar) fromDate.clone(), toDate));
			MetricByWeek lastMbw = null;

			for (MetricByWeek mbw : metricsByWeek) {
				if (!currentMetricId.equals(mbw.getMetric().getId())) {
					if (!currentReportSuiteId.equals(mbw.getReportSuite()
							.getId())) {
						if (currentReportSuiteId != 0) {
							checkForUpperMetrics(currentDate, fromDate, toDate,
									fw, currentMetricId, lastMbw,
									processedMetrics, sb);
						}
						currentReportSuiteId = mbw.getReportSuite().getId();
						processedMetrics.clear();
					}
					currentMetricId = mbw.getMetric().getId();
					checkForLowerMetrics(currentDate, fromDate, toDate, fw,
							currentMetricId, mbw, processedMetrics, sb);

					if (sb.length() > 0) {
						sb.append("</tr>");
						fw.append(sb.toString());

						sb.setLength(0);
						currentDate = (Calendar) fromDate.clone();
						System.gc();
					}
					saveLineAndCreateMetricDetail(sb, currentDate, fromDate,
							fw, currentMetricId, mbw);

				}
				metricDate.setTime(mbw.getMetricDate());
				while (currentDate.before(metricDate)
						&& currentDate.before(toDate)) {
					sb.append("<td>&nbsp;</td>");
					currentDate.add(Calendar.DATE, 7);
				}
				sb.append("<td>" + mbw.getMetricDate() + "</td>");
				currentDate.add(Calendar.DATE, 7);
				lastMbw = mbw;
			}
			if (currentReportSuiteId != 0) {
				checkForUpperMetrics(currentDate, fromDate, toDate, fw,
						currentMetricId, lastMbw, processedMetrics, sb);
				fw.append(sb.toString());
			}
			fw.append("</table>");
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el archivo");
					e.printStackTrace();
				}
			}
		}
	}

	private void checkForUpperMetrics(Calendar currentDate, Calendar fromDate,
			Calendar toDate, FileWriter fw, Integer currentMetricId,
			MetricByWeek mbd, Set<Integer> processedMetrics, StringBuilder sb)
			throws IOException {
		for (Integer metric : Metric.METRICS) {
			processedMetrics.add(metric);
			if (metric > currentMetricId) {
				processMetric(metric, fw, fromDate, toDate, mbd, sb,
						currentDate);
			}
		}
	}

	private void checkForLowerMetrics(Calendar currentDate, Calendar fromDate,
			Calendar toDate, FileWriter fw, Integer currentMetricId,
			MetricByWeek mbw, Set<Integer> processedMetrics, StringBuilder sb)
			throws IOException {
		for (Integer metric : Metric.METRICS) {
			if (metric < currentMetricId && !processedMetrics.contains(metric)) {
				processMetric(metric, fw, fromDate, toDate, mbw, sb,
						currentDate);
			}
			processedMetrics.add(metric);
			if (metric >= currentMetricId) {
				break;
			}
		}
	}

	private void processMetric(Integer metric, FileWriter fw,
			Calendar fromDate, Calendar toDate, MetricByWeek mbw,
			StringBuilder sb, Calendar currentDate) throws IOException {
		if (sb.length() > 0) {
			sb.append("</tr>");
			fw.append(sb.toString());

			sb.setLength(0);
			currentDate = (Calendar) fromDate.clone();
			System.gc();
		}
		if (Metric.DASHBOARD_METRICS.contains(metric)) {
			sb.append("<tr style='background-color:rgb(87,201,101);'>");
		} else {
			sb.append("<tr>");
		}
		sb.append("<td>" + mbw.getReportSuite().getId() + "</td>");
		sb.append("<td>" + mbw.getReportSuite().getName() + "</td>");
		sb.append("<td>" + metric + "</td>");
		sb.append("<td>&nbsp;</td>");
		sb.append("<td>" + mbw.getElement().getId() + "</td>");
		sb.append("<td>" + mbw.getElement().getName() + "</td>");

		while (currentDate.before(toDate) && currentDate.before(toDate)) {
			sb.append("<td>&nbsp;</td>");
			currentDate.add(Calendar.DATE, 7);
		}
	}

	private void saveLineAndCreateMetricDetail(StringBuilder sb,
			Calendar currentDate, Calendar fromDate, FileWriter fw,
			Integer currentMetricId, MetricByWeek mbw) throws IOException {

		if (Metric.DASHBOARD_METRICS.contains(currentMetricId)) {
			sb.append("<tr style='background-color:rgb(87,201,101);'>");
		} else {
			sb.append("<tr>");
		}
		sb.append("<td>" + mbw.getReportSuite().getId() + "</td>");
		sb.append("<td>" + mbw.getReportSuite().getName() + "</td>");
		sb.append("<td>" + mbw.getMetric().getId() + "</td>");
		sb.append("<td>" + mbw.getMetric().getMetricCode() + "</td>");
		sb.append("<td>" + mbw.getElement().getId() + "</td>");
		sb.append("<td>" + mbw.getElement().getName() + "</td>");
	}

	private String generateHeader(Calendar fromDate, Calendar toDate) {
		StringBuilder header = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		header.append("<tr>");
		header.append("<th>Report Suite Id</th>");
		header.append("<th>Report Suite</th>");
		header.append("<th>Metric Id</th>");
		header.append("<th>Metric</th>");
		header.append("<th>Element Id</th>");
		header.append("<th>Element</th>");
		while (fromDate.before(toDate)) {
			header.append("<th>" + sdf.format(fromDate.getTime()) + "</th>");
			fromDate.add(Calendar.DATE, 7);
		}
		header.append("<th>" + sdf.format(fromDate.getTime()) + "</th>");
		header.append("</tr>");
		return header.toString();
	}

}
