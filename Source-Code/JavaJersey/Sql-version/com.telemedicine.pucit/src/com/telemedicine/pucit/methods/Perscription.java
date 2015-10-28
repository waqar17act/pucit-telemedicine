package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.telemedicine.pucit.methods.log.LogClass;

public class Perscription extends DataBaseConnect{
	
	public int insertPerscription(String persId, String visitid, String docid, String feedback) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			
			con = databaseConnection();
			
			query = con.prepareStatement("INSERT INTO perscription VALUES (?,?,?,?)");
			query.setString(1, persId);
			query.setString(2, visitid);
			query.setString(3, docid);
			query.setString(4, feedback);
			
			query.executeUpdate();
			
			LogClass lc = new LogClass();
			lc.logEvent("Doctor Id:"+docid, "Add Perscription");
			
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
