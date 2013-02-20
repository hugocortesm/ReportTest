package com.pech.webdashboard.reporttest.data;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class ConnectionManager {

	private static Connection connection = null;
	private static int REMOTE_PORT = 13306;
	private static int LOCAL_PORT = 3306;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error cargando el driver MYSQL");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + LOCAL_PORT
								+ "/sitecatalyst", "shawmedia", "shawmedia");
			} catch (SQLException e) {
				System.out.println("Error obteniedo la conexi�n");
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void closeConnection() {
		System.out.println("Clossing connection");
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException sqle) {
			System.out.println("Error cerrando la conexions");
			sqle.printStackTrace();
		}
	}

}
