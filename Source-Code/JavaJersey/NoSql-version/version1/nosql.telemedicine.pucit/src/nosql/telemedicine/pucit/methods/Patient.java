package nosql.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.log.LogClass;

public class Patient extends DataBaseConnect{
	
	public int insertPatient(String patId, String regdate, String bloodgroup, String nic, String dob, String persid) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		try
		{
			
			con = databaseConnection();
		
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("patient");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("patid", patId);
			document.put("regdate", regdate);
			document.put("bloodgroup", bloodgroup);
			document.put("nic", nic);
			document.put("dob", dob);
			document.put("persid", persid);
			table.insert(document);
			
			
			LogClass lc = new LogClass();
			lc.logEvent("Patient id:"+patId, "Add Patient");
			
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
