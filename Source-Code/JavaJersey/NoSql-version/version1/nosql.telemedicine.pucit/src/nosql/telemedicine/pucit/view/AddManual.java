package nosql.telemedicine.pucit.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import nosql.telemedicine.pucit.methods.Person;
import nosql.telemedicine.pucit.methods.ToJson;
import nosql.telemedicine.pucit.methods.DaoClass;
import nosql.telemedicine.pucit.methods.empClass;


@Path("/addaperson")
public class AddManual {

	@POST
	@Produces(MediaType.TEXT_HTML)
	public String One(
			@QueryParam("name") String name)
			throws Exception {
		
		System.out.println("Person Values are: ");
		Person person = new Person();
		int httpCode = person.insertPerson("as", "as", "as", "as", "as");
return "asd -- "+httpCode;
	}

}
