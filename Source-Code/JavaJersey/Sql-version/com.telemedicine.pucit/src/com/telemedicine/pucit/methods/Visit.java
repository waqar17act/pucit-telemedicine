package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.telemedicine.pucit.methods.log.LogClass;

public class Visit extends DataBaseConnect{
	
	public int insertVisit(String visitId, String time, String type, String location, String patid) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		try
		{
			
			con = databaseConnection();
			
			query = con.prepareStatement("INSERT INTO visit VALUES (?,?,?,?,?)");
			query.setString(1, visitId);
			query.setString(2, time);
			query.setString(3, type);
			query.setString(4, location);
			query.setString(5, patid);
			
			query.executeUpdate();
		
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
				con.close();
			}
			
		}
		
		return 200;
	}

}
