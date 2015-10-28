package nosql.telemedicine.pucit.methods;

import com.mongodb.DBCursor;

public class ToJson {

	public String toJsonArray (DBCursor res)
	{
		return com.mongodb.util.JSON.serialize(res);
	}
}
