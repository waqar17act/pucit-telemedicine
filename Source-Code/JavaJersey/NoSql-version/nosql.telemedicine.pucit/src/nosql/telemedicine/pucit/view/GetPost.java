package nosql.telemedicine.pucit.view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import nosql.telemedicine.pucit.methods.ToJson;
import nosql.telemedicine.pucit.methods.DaoClass;
import nosql.telemedicine.pucit.methods.empClass;



@Path("/saveperson")
public class GetPost {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnOne(
			@QueryParam("name") String name)
			throws Exception {
	
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try 
		{
			if(name == null) {
				return Response.status(400).entity("Error: please specify person Name for this search").build();
			}
			
			
			DBCollection table = MongoConnection.Connect();

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("persid", giveRandomString(5));
			document.put("name", name);
			document.put("username", "");
			document.put("password", "");
			document.put("role", giveRandomString(5));
			document.put("contact", giveRandomString(15));
			document.put("affiliation", "");
			document.put("registration_date", "");
			document.put("gender", giveRandomString(4));
			document.put("nic", "");
			document.put("dob", "");
			document.put("blood_group", "");
			document.put("description", "");
			table.insert(document);

			returnString = json.toString();
			System.out.println("Inserted");
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
	
		return Response.ok(returnString).build();
	}
	private String giveRandomString( int length)
	{
	
		final String MasterString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final Random rnd = new Random();
		
	   StringBuilder sb = new StringBuilder( length );
	   for( int i = 0; i < length; i++ ) 
	      sb.append( MasterString.charAt( rnd.nextInt(MasterString.length()) ) );
	   return sb.toString();
	}

}
