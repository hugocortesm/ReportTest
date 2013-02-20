package com.pech.webdashboard.reporttest.vo;

import java.io.Serializable;
import java.util.Date;

public class MetricByDay implements Serializable {

	private static final long serialVersionUID = -6404677130951514791L;

	private Integer id;
	private ReportSuite reportSuite;
	private Metric metric;
	private Date metricDate;
	private Double metricValue;
	private Element element;
	private String elementValue;

	public MetricByDay() {
		reportSuite = new ReportSuite();
		metric = new Metric();
		element = new Element();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReportSuite getReportSuite() {
		return reportSuite;
	}

	public void setReportSuite(ReportSuite reportSuite) {
		this.reportSuite = reportSuite;
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public Date getMetricDate() {
		return metricDate;
	}

	public void setMetricDate(Date metricDate) {
		this.metricDate = metricDate;
	}

	public Double getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(Double metricValue) {
		this.metricValue = metricValue;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

}
