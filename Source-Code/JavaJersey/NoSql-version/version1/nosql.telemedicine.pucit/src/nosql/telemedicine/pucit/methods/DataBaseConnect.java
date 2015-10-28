package nosql.telemedicine.pucit.methods;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DataBaseConnect {
	private static DataSource mysqlDatasource = null;
	private static Context context = null;
	
	/*public static DataSource MysqlConnection() throws Exception
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
*/
	protected static DB databaseConnection() {
		 DB db = null;
		try {
			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			db = mongo.getDB("telemedicine");

			return db;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}

}
