package com.telemedicine.pucit.methods;

import java.awt.image.BufferedImage;

import org.apache.commons.codec.binary.Base64;

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

import com.telemedicine.pucit.methods.Base64.InputStream;
import com.telemedicine.pucit.methods.log.LogClass;

import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


public class VisitData extends DataBaseConnect{
	
	public int insertVisitData(String visitdataId, String description, String visitid, String imgpath, String imgtype) throws Exception
	{
		PreparedStatement query = null;
		Connection con = null;
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
			
			query = con.prepareStatement("INSERT INTO visitdata VALUES (?,?,?,?,?)");
			
			query.setString(1, visitdataId);
			query.setString(2, description);
			query.setString(3, visitid);
			query.setString(4, imgpath);
			query.setString(5, imgtype);
			
			query.executeUpdate();
			
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
				con.close();
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



