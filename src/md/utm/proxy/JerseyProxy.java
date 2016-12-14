package md.utm.proxy;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import md.utm.DW.MongoDB;

@Path("/resurces")
public class JerseyProxy {
	static Gson gson = new Gson();
	

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTrackInJSON() {

		String s="{\"nodeId\":\"lalala\"}";
		try {
		String	workerColection = new String(Files.readAllBytes(Paths.get("col0.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fsdfgsdfsf";

	}
	@GET
	@Path("/workers/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWorkers() {

		Client client = Client.create();
int port=6003+balancing();
		WebResource webResource = client
		   .resource("http://localhost:"+port+"/resurces/workers/get");

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
	
/*MongoDB md= new MongoDB();
		
		String workerColection = gson.toJson(md.getFromDB("workers"));*/
	
		return output;

	}
	public int balancing()
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2);
		return randomInt;
	
	}
}