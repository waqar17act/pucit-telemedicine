package nosql.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import nosql.telemedicine.pucit.methods.VisitData;

@Path("/addvisitdata")
public class VisitDataAdd {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertVisitData(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		VisitData visitdata = new VisitData();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			visitdataFields visitdataFields = mapper.readValue(values,visitdataFields.class);
			System.out.println("Visit Data Values are: "+ values);
			int httpCode = visitdata.insertVisitData(visitdataFields.visitdataid, visitdataFields.description, visitdataFields.visitid,visitdataFields.imgpath, visitdataFields.imgtype);
			
			if(httpCode == 200)
			{
				retString = "This Visit Data is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this Visit Data").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this Visit Data").build();
		}

		return Response.ok(retString).build();
	}
	
}

class visitdataFields
{
	public String visitdataid;
	public String description;
	public String visitid;
	public String imgpath;
	public String imgtype;
}