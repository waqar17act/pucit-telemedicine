package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.telemedicine.pucit.methods.log.LogClass;

public class Admin extends DataBaseConnect{
	
	public int insertAdmin(String admId, String username, String password, String description, String persid) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			
			con = databaseConnection();
			
			query = con.prepareStatement("INSERT INTO admin VALUES (?,?,?,?,?)");
			query.setString(1, admId);
			query.setString(2, username);
			query.setString(3, password);
			query.setString(4, description);
			query.setString(5, persid);
			
			query.executeUpdate();
			
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
			if(con!= null)
			{
				con.close();
			}
			
		}
		
		return 200;
	}

}
