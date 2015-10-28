package com.telemedicine.pucit.methods.log;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.telemedicine.pucit.methods.DataBaseConnect;

public class LogClass extends DataBaseConnect {
	
	public void logEvent(String username, String method) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
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
			query = con.prepareStatement("INSERT INTO audit VALUES (?,?,?,?)");
			query.setString(1, ip);
			query.setString(2, username);
			query.setString(3, datetime);
			query.setString(4, method);
			
			query.executeUpdate();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(con!= null)
			{
				con.close();
			}
			
		}
		
	}


}
