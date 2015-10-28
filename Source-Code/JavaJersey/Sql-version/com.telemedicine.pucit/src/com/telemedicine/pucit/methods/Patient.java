package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.telemedicine.pucit.methods.log.LogClass;

public class Patient extends DataBaseConnect{
	
	public int insertPatient(String patId, String regdate, String bloodgroup, String nic, String dob, String persid) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			
			con = databaseConnection();
			
			query = con.prepareStatement("INSERT INTO patient VALUES (?,?,?,?,?,?)");
			query.setString(1, patId);
			query.setString(2, regdate);
			query.setString(3, bloodgroup);
			query.setString(4, nic);
			query.setString(5, dob);
			query.setString(6, persid);
			
			query.executeUpdate();
			
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
			if(con!= null)
			{
				con.close();
			}
			
		}
		
		return 200;
	}

}
