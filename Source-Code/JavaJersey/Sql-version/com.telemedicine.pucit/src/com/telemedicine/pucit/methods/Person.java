package com.telemedicine.pucit.methods;


import java.sql.*;

import com.telemedicine.pucit.methods.log.LogClass;



public class Person extends DataBaseConnect{

	public int insertPerson(String persId, String name, String gender, String role, String contact) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			con = databaseConnection();	
			query = con.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?)");
			query.setString(1, persId);
			query.setString(2, name);
			query.setString(3, gender);
			query.setString(4, role);
			query.setString(5, contact);
			
			query.executeUpdate();
			
			LogClass lc = new LogClass();
			lc.logEvent(name, "Add Person");
			
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
