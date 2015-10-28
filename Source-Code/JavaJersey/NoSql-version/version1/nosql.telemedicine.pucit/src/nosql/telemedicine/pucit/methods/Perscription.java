package nosql.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.log.LogClass;

public class Perscription extends DataBaseConnect{
	
	public int insertPerscription(String persId, String visitid, String docid, String feedback) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		try
		{
			
			con = databaseConnection();
	
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("perscription");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("perscriptid", persId);
			document.put("visitid", visitid);
			document.put("docid", docid);
			document.put("feedback", feedback);
			table.insert(document);
	
			LogClass lc = new LogClass();
			lc.logEvent("Doctor Id:"+docid, "Add Perscription");
			
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
