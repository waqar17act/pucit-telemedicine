package nosql.telemedicine.pucit.methods;


import java.sql.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import nosql.telemedicine.pucit.methods.log.LogClass;



public class Person extends DataBaseConnect{

	public int insertPerson(String persId, String name, String gender, String role, String contact) throws Exception
	{
		try
		{
			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("telemedicine");
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("person");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("persid", persId);
			document.put("name", name);
			document.put("gender", gender);
			document.put("role", role);
			document.put("contact", contact);
			table.insert(document);
			
			
			//LogClass lc = new LogClass();
			//lc.logEvent(name, "Add Person");
			
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
