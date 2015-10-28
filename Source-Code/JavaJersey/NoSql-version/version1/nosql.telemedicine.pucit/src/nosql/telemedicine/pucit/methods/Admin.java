package nosql.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.log.LogClass;

public class Admin extends DataBaseConnect{
	
	public int insertAdmin(String admId, String username, String password, String description, String persid) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		try
		{
			
			con = databaseConnection();
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("admin");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("admid", admId);
			document.put("username", username);
			document.put("password", password);
			document.put("description", description);
			document.put("persid", persid);
			table.insert(document);

			
			LogClass lc = new LogClass();
			lc.logEvent(username, "Add Admin");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return 500;
		}
		finally
		{
			
		}
		
		return 200;
	}

}
