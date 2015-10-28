package nosql.telemedicine.pucit.methods.log;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import nosql.telemedicine.pucit.methods.DataBaseConnect;

public class LogClass extends DataBaseConnect {
	
	public void logEvent(String username, String method) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		String ip = null, datetime = null;
		
		try
		{
			//getting IP address 
			InetAddress ownIP = InetAddress.getLocalHost();	
			ip = ownIP.getHostAddress();
			
			// Getting time of invocation
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
			Date date = new Date();
			datetime = dateFormat.format(date);
			
			con = databaseConnection();
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("audit");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("ip", ip);
			document.put("username", username);
			document.put("datetime", datetime);
			document.put("method", method);
			table.insert(document);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(con!= null)
			{
			}
			
		}
		
	}


}
