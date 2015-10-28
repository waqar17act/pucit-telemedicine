package nosql.telemedicine.pucit.test;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.commons.codec.binary.Base64;

	/**
	 * @desc Image manipulation - Conversion
	 * 
	 * @filename ImageManipulation.java
	 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam Madanagopal</a>
	 * @copyright &copy; 2010-2012 www.myjeeva.com
	 */
	public class Test {

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			File file = new File("D://Data Repository//Photos//misc\\22.jpg");
			
			try {
				/*
				 * Reading a Image file from file system
				 */
				FileInputStream imageInFile = new FileInputStream(file);
				byte imageData[] = new byte[(int)file.length()];
				imageInFile.read(imageData);
				System.out.println(imageInFile);
				/*
				 * Converting Image byte array into Base64 String 
				 */
				String imageDataString = encodeImage(imageData);
				
				/*
				 * Converting a Base64 String into Image byte array 
				 */
				byte[] imageByteArray = decodeImage(imageDataString);
				
				/*
				 * Write a image byte array into file system  
				 */
				FileOutputStream imageOutFile = new FileOutputStream("c:\\temp\\Imahe.jpg");
				imageOutFile.write(imageByteArray);
				
				imageInFile.close();
				imageOutFile.close();
				
				System.out.println("Image Successfully Manipulated!");
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading the Image " + ioe);
			}

		}
		
		/**
		 * Encodes the byte array into base64 string
		 * @param imageByteArray - byte array
		 * @return String a {@link java.lang.String}
		 */
		public static String encodeImage(byte[] imageByteArray){		
			return Base64.encodeBase64URLSafeString(imageByteArray);		
		}
		
		/**
		 * Decodes the base64 string into byte array
		 * @param imageDataString - a {@link java.lang.String} 
		 * @return byte array
		 */
		public static byte[] decodeImage(String imageDataString) {		
			return Base64.decodeBase64(imageDataString);
		}

	}