package nosql.telemedicine.pucit.methods;

import javax.naming.*;
import javax.sql.*;


public class DaoClass {

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
	
}
