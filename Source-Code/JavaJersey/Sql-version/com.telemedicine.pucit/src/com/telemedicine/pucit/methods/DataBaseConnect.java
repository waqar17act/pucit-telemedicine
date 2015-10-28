
package com.telemedicine.pucit.methods;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataBaseConnect {
	private static DataSource mysqlDatasource = null;
	private static Context context = null;
	
	public static DataSource MysqlConnection() throws Exception
	{
		try
		{
			if(context == null)
			{
				context = new InitialContext();				
			}
			
			mysqlDatasource = (DataSource) context.lookup("medicine");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return mysqlDatasource;
	}

	protected static Connection databaseConnection() {
		Connection conn = null;
		try {
			conn = MysqlConnection().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
