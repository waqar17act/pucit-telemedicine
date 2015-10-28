package nosql.telemedicine.pucit.view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			

			
			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			//MongoClient mongo = new MongoClient("localhost", 27017);
			MongoClient mongo = new MongoClient("54.212.250.21", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("telemedicine");
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("person");
			
			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			String name1 =  giveRandomString(6);
			document.put("persid", giveRandomString(5));
			document.put("name", name1);
			document.put("gender", giveRandomString(4));
			document.put("role", giveRandomString(4));
			document.put("contact", giveRandomString(15));
			table.insert(document);

			returnString = json.toString();
			mongo.close();
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
