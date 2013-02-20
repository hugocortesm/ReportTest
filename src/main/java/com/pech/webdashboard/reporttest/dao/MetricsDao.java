package com.pech.webdashboard.reporttest.dao;

import java.util.List;

import com.pech.webdashboard.reporttest.vo.Metric;

public interface MetricsDao {

	List<Metric> loadAll();

}
