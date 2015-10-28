package nosql.telemedicine.pucit.methods;

import java.sql.PreparedStatement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.log.LogClass;

public class Visit extends DataBaseConnect{
	
	public int insertVisit(String visitId, String time, String type, String location, String patid) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		try
		{
			
			con = databaseConnection();
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("visit");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("visitid", visitId);
			document.put("visitime", time);
			document.put("visitype", type);
			document.put("visitloc", location);
			document.put("patid", patid);
			table.insert(document);

			LogClass lc = new LogClass();
			lc.logEvent("VisitId: "+visitId, "Add Visit");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return 500;
		}
		finally
		{
			if(con!= null)
			{
			}
			
		}
		
		return 200;
	}

}
