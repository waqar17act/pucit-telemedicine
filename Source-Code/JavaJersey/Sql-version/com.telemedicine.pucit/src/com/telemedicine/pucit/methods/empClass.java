package com.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.codehaus.jettison.json.JSONArray;






import com.telemedicine.pucit.methods.ToJson;

public class empClass extends DataBaseConnect {

	public JSONArray querySaveEmployee(String name) throws Exception {
		
		PreparedStatement query = null;
		Connection con = null;
		
		ToJson converter = new ToJson();
		JSONArray json = new JSONArray();
		
		try {
			
			
			System.out.println("Going to insert");
			con = databaseConnection();		
			query = con.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?,?)");
			query.setString(1, name);
			query.setString(2, giveRandomString(5));
			query.setString(3, giveRandomString(5));
			query.setString(4, giveRandomString(5));
			query.setString(5, giveRandomString(5));
			query.setString(6,null);
			
			query.executeUpdate();
			
			
			System.out.println("Inserted");
			
			
			
			
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (con != null) con.close();
		}
		
		return json;
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
	
	public JSONArray queryReturnEmployee(String name) throws Exception {
			
			PreparedStatement query = null;
			Connection conn = null;
			
			ToJson converter = new ToJson();
			JSONArray json = new JSONArray();
			
			try {
				conn = DaoClass.MysqlConnection().getConnection();
				query = conn.prepareStatement("select * from person where name = ? limit 5");
				
				query.setString(1, name); //protect against sql injection
				ResultSet rs = query.executeQuery();
				
				json = converter.toJsonArray(rs);
				query.close(); //close connection
				
				System.out.println("read request");
			}
			catch(SQLException sqlError) {
				sqlError.printStackTrace();
				return json;
			}
			catch(Exception e) {
				e.printStackTrace();
				return json;
			}
			finally {
				if (conn != null) conn.close();
			}
			
			return json;
		}

	public JSONArray queryReturnFiftyEmployee(String name) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJson converter = new ToJson();
		JSONArray json = new JSONArray();
		
		try {
			conn = DaoClass.MysqlConnection().getConnection();
			query = conn.prepareStatement("select * from person where name = ? limit 5000");
			
			query.setString(1, name); //protect against sql injection
			ResultSet rs = query.executeQuery();
			json = converter.toJsonArray(rs);
			System.out.println("read request"+rs.getFetchSize());
			query.close(); //close connection
			

		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

	
	public JSONArray queryReturnLikeEmployee(String name) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJson converter = new ToJson();
		JSONArray json = new JSONArray();
		
		try {
			conn = DaoClass.MysqlConnection().getConnection();
			query = conn.prepareStatement("select * from person where name = ? ");
			
			query.setString(1, name); //protect against sql injection
			ResultSet rs = query.executeQuery();
			
			json = converter.toJsonArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

	public int deleteEmployee(String pk) throws Exception {
			
			PreparedStatement query = null;
			Connection conn = null;
			
			try {
				/*
				 * If this was a real application, you should do data validation here
				 * before deleting data.
				 */
				
				conn = DaoClass.MysqlConnection().getConnection();
				query = conn.prepareStatement("delete from person " +
												"where name ='"+pk+"'");
				
//				query.setString(0, pk);
				query.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
				return 500;
			}
			finally {
				if (conn != null) conn.close();
			}
			
			return 200;
		}

	}


