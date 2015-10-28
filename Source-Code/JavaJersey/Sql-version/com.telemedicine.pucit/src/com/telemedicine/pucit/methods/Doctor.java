package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.telemedicine.pucit.methods.log.LogClass;

public class Doctor extends DataBaseConnect{
	
	public int insertDoctor(String docId, String affiliation, String username, String password, String description, String dob, String persid) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			
			con = databaseConnection();
			
			query = con.prepareStatement("INSERT INTO doctor VALUES (?,?,?,?,?,?,?)");
			query.setString(1, docId);
			query.setString(2, affiliation);
			query.setString(3, username);
			query.setString(4, password);
			query.setString(5, description);
			query.setString(6, dob);
			query.setString(7, persid);
			
			query.executeUpdate();
			
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
			if(con!= null)
			{
				con.close();
			}
			
		}
		
		return 200;
	}

}
