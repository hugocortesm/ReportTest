package com.pech.webdashboard.reporttest.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public interface MetricsByDayBo {

	public void generateReport(Calendar fromCal, Calendar toCal, String file)
			throws IOException, SQLException;

}
