package nosql.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.log.LogClass;

public class Doctor extends DataBaseConnect{
	
	public int insertDoctor(String docId, String affiliation, String username, String password, String description, String dob, String persid) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		try
		{
			
			con = databaseConnection();
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("doctor");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("docid", docId);
			document.put("affiliation", affiliation);
			document.put("username", username);
			document.put("password", password);
			document.put("description", description);
			document.put("dob", dob);
			document.put("persid", persid);
			table.insert(document);
			
			LogClass lc = new LogClass();
			lc.logEvent(username, "Add Doctor");
			
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
