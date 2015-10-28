package nosql.telemedicine.pucit.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.codehaus.jettison.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import nosql.telemedicine.pucit.methods.ToJson;
import nosql.telemedicine.pucit.view.MongoConnection;

public class empClass {

		public String queryReturnEmployee(String name) throws Exception {
			
			ToJson converter = new ToJson();
			//JSONArray json = new JSONArray();
			String json = "";
			try {

	
				DBCollection table = MongoConnection.Connect();

				/**** Find and display ****/
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("name", name);
				DBCursor cursor = table.find(searchQuery).limit(100);
				System.out.println("found");
				System.out.println(cursor.toString());
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
				System.out.println("Read");
				json = converter.toJsonArray(cursor);
				
				
			
			}
			catch (MongoException e) {
				e.printStackTrace();
			}
			catch(Exception Error) {
				Error.printStackTrace();
				return json;
			}
			finally {
//				if (conn != null) conn.close();
			}
			
			return json;
		}
		public String returnAllEmployees() throws Exception {
			
			ToJson converter = new ToJson();
			//JSONArray json = new JSONArray();
			String json = "";
			try {

	
				/**** Connect to MongoDB ****/
				// Since 2.10.0, uses MongoClient
				MongoClient mongo = new MongoClient("localhost", 27017);
//				MongoClient mongo = new MongoClient("54.214.0.233", 27017);
				
				/**** Get database ****/
				// if database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("telemedicine");

				/**** Get collection / table from 'testdb' ****/
				// if collection doesn't exists, MongoDB will create it for you
				DBCollection table = db.getCollection("person");

				/**** Find and display ****/
				BasicDBObject searchQuery = new BasicDBObject();
				//searchQuery.put("name", name);

				DBCursor cursor = table.find();
				
				System.out.println(cursor.count());
				
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}

				json = converter.toJsonArray(cursor);

			
			}
			catch (MongoException e) {
				e.printStackTrace();
			}
			catch(Exception Error) {
				Error.printStackTrace();
				return json;
			}
			finally {
//				if (conn != null) conn.close();
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


