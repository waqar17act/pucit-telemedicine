package com.telemedicine.pucit.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.telemedicine.pucit.methods.Patient;

@Path("/addpatient")
public class PatientAdd {

	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPatient(String values) throws Exception
	{
		String retString = null;
		JSONArray jsonArray = new JSONArray();
		Patient patient = new Patient();
		try
		{
			
			ObjectMapper mapper = new ObjectMapper();
			
			patientFields patFields = mapper.readValue(values,patientFields.class);
			System.out.println("Patient Values are: "+ values);
			int httpCode = patient.insertPatient(patFields.patid, patFields.regdate, patFields.bloodgroup, patFields.nic, patFields.dob, patFields.persid);
			
			if(httpCode == 200)
			{
				retString = "This patient is saved successfully";
			}
			else
			{
				return Response.status(500).entity("Server is unable to insert this patient").build();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return Response.status(500).entity("Server is unable to insert this patient").build();
		}

		return Response.ok(retString).build();
	}
	
}

class patientFields
{
	public String patid;
	public String regdate;
	public String bloodgroup;
	public String nic;
	public String dob;
	public String persid;
}