package com.telemedicine.pucit.methods;

import org.codehaus.jettison.json.JSONArray;
import org.owasp.esapi.ESAPI;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

public class ToJson {

	public JSONArray toJsonArray (ResultSet res) throws Exception
	{
		JSONArray json = new JSONArray();
		try
		{
			java.sql.ResultSetMetaData rmeta = res.getMetaData();

			while(res.next())
			{
				int countCols = rmeta.getColumnCount();
				
				JSONObject obj = new JSONObject();
				
				for(int i=1 ; i<countCols+1 ; i++)
				{
					String colName = rmeta.getColumnName(i);
					if(rmeta.getColumnType(i)== java.sql.Types.VARCHAR)
					{
						obj.put(colName, res.getString(colName));
					}
					
				}
				json.put(obj);
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			
		}
		return json;
	}
}
