package com.telemedicine.pucit.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.DaoClass;
import com.telemedicine.pucit.methods.ToJson;


@Path("/getallpersc")
public class FetchPerscriptions {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String retStatus() throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
		String retString = null;
		
		try
		{
			con = DaoClass.MysqlConnection().getConnection();
			query = con.prepareStatement("select *"
					+ "from perscription");
			
			ResultSet rs = query.executeQuery();
			
			ToJson convertor = new ToJson();
			JSONArray json = new JSONArray();
			
			json = convertor.toJsonArray(rs);
			query.close();
			retString = json.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
		if(con !=null)
		{
			con.close();
		}	
		}
		return retString;
		
	}

}
