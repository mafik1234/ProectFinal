package md.utm.proxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import md.utm.DW.MongoDB;
import redis.clients.jedis.Jedis;

@Path("/resurces")
public class JerseyProxy {
	static Gson gson = new Gson();
	int port = 6003;
	Jedis jedis = new Jedis("localhost");

	
	


	@GET
	@Path("/workers/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWorkers() {
		String output= jedis.get("/resurces/workers/get");
		
		if (output==null){
			output=getResurceFromDW("/resurces/workers/get");
			System.out.println("Output from Server .... \n");
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);

	
		return output;

	}
	
	@GET
	@Path("/workers/get/filter")
	@Produces(MediaType.APPLICATION_JSON)
	public String filterWorkers(@QueryParam("salary") int salary) {

String output= jedis.get("/resurces/workers/get/filter/"+salary);
		
		if (output==null){
			output=getResurceFromDW("/resurces/workers/get/filter","salary",""+salary);
			System.out.println("Output from Server .... \n");
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);
		return output;
	}
	
public String getResurceFromDW(String path,String paramName,String param)
{
	port = 6003 + balancing();
	String output;
	Client client = Client.create();
	WebResource webResource = client.resource("http://localhost:" + port +path);
	ClientResponse response = webResource.queryParam(paramName, param).accept("application/json").get(ClientResponse.class);
	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}

	 output = response.getEntity(String.class);
		jedis.set(path+"/"+param, output);
		jedis.expire(path+"/"+param, 10);
	return output;
}
public String getResurceFromDW(String path)
{
	//port = 6003 + balancing();
	String output;
	Client client = Client.create();
	WebResource webResource = client.resource("http://localhost:" + port + path);
	ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}

	 output = response.getEntity(String.class);
		jedis.set(path, output);
		jedis.expire(path, 10);
	return output;
}
	public int balancing() {
		System.out.println("Balancing .... \n");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2);
		return randomInt;

	}
}