package nosql.telemedicine.pucit.view;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection {

	private static DBCollection collection = null;


		static MongoClient mongo = null;
		static DB db = null;
	
	public static DBCollection Connect() 
	{
		/**** Connect to MongoDB ****/
		// Since 2.10.0, uses MongoClient
	
		try 
		{
			if(collection == null)
			{
				mongo = new MongoClient("localhost", 27017);
				//mongo = new MongoClient("54.212.250.21", 27017);
				
				db = mongo.getDB("telemedicine");
				collection = db.getCollection("person");
				return collection;
			}
			else
			{
				return collection;
			}

		}
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}
	
}


