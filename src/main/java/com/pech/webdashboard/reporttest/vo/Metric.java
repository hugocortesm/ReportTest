package com.pech.webdashboard.reporttest.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Metric implements Serializable {

	private static final long serialVersionUID = -4981080812994447579L;
	public static List<Integer> CALCULATED_METRICS = new ArrayList<Integer>() {
		private static final long serialVersionUID = 904717200432763559L;
		{
			add(6);
			add(11);
			add(14);
			add(15);
		}
	};
	public static TreeSet<Integer> METRICS = new TreeSet<Integer>() {
		private static final long serialVersionUID = 904717200432763559L;
		{
			add(4);
			add(5);
			add(6);
			add(7);
			add(10);
			add(11);
			add(14);
			add(15);
			add(16);
			add(19);
			add(20);
			add(21);
			add(22);
			add(23);
			add(24);
			add(25);
			add(26);
			add(27);
			add(28);
			add(29);
			add(30);
			add(31);
			add(32);
			add(33);
			add(34);
			add(35);
		}
	};
	public static List<Integer> DASHBOARD_METRICS = new ArrayList<Integer>() {
		private static final long serialVersionUID = 904717200432763559L;
		{
			add(4);
			add(5);
			add(6);
			add(7);
			add(10);
			add(11);
			add(14);
			add(15);
		}
	};

	private Integer id;
	private String metricCode;
	private String name;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetricCode() {
		return metricCode;
	}

	public void setMetricCode(String metricCode) {
		this.metricCode = metricCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
