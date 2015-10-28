package nosql.telemedicine.pucit.methods;

import org.apache.commons.codec.binary.Base64;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.ws.rs.Path;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayOutputStream;

import nosql.telemedicine.pucit.methods.Base64.InputStream;
import nosql.telemedicine.pucit.methods.log.LogClass;



//import java.nio.file.CopyOption;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


public class VisitData extends DataBaseConnect{
	
	public int insertVisitData(String visitdataId, String description, String visitid, String imgpath, String imgtype) throws Exception
	{
		PreparedStatement query = null;
		DB con = null;
		String imageName = null;
		try
		{
			// Getting time of invocation
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			   //get current date time with Date()
			Date date = new Date();
			imageName = dateFormat.format(date);
			
			// START OF BASE64 TO IMAGE WRITE
			
			String imageDataBytes = imgpath.substring(imgpath.indexOf(",")+1);
						
			// Converting a Base64 String into Image byte array 
			byte[] imageByteArray = Base64.decodeBase64(imageDataBytes);
			
			// Write a image byte array into file system  
			FileOutputStream imageOutFile = new FileOutputStream(new File("c:\\temp\\"+visitdataId+""+imageName+".jpg"));
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
		
			System.out.println("Image Successfully Manipulated!");
			
			//END OF BASE64 TO IMAGE WRITE
			
			imgpath = "c:\\temp\\"+visitdataId+""+imageName+".jpg";
			con = databaseConnection();
			
			/**** Get collection / table from 'telemedicine' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = con.getCollection("visitdata");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("visitdataId", visitdataId);
			document.put("description", description);
			document.put("visitid", visitid);
			document.put("imgpath", imgpath);
			document.put("imgtype", imgtype);
			table.insert(document);
			
			
			LogClass lc = new LogClass();
			lc.logEvent("VisitDataId:"+visitdataId, "Add Visit Data");
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
			}
		}
		
		return 200;
	}

	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
}



