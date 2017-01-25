package md.utm.proxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import md.utm.DW.MongoDB;
import redis.clients.jedis.Jedis;
import tools.XstreamTool;
import collections.Worker;
import collections.WorkerList;
@Path("/resurces")
public class JerseyProxy {
	static Gson gson = new Gson();
	int port = 7002;
	Jedis jedis = new Jedis("localhost");
	XstreamTool xs= new XstreamTool();
	
	


	@GET
	@Path("/workers/get/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWorkersJson() {
		String path="/resurces/workers/get";
		String output= jedis.get(path);
		
		if (output==null){
			output=getResurceFromDW(path);
			System.out.println("Output from Server .... \n");
			jedis.set(path, output);
			jedis.expire(path, 10);
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);

	
		return output;

	}
	
	@GET
	@Path("/workers/get/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String getWorkersXml() throws IOException {
		
	
		String pathXml="/resurces/workers/get/xml";
		String path="/resurces/workers/get";
		
		String output= jedis.get(pathXml);
		
		if (output==null){
			output=getResurceFromDW(path);
			System.out.println("Output from Server .... \n");
			output=xs.listToXml(jsonToList(output));	
			jedis.set(pathXml, output);
			jedis.expire(pathXml, 10);
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);

	
		return output;

	}
	
	
	
	@GET
	@Path("/workers/get/filter/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String filterWorkersJson(@QueryParam("salary") int salary) throws IOException {
		String path="/resurces/workers/get/filter";
String output= jedis.get(path+salary);
		
		if (output==null){
			output=getResurceFromDW(path,"salary",""+salary);
			System.out.println("Output from Server .... \n");
			
			jedis.set(path+"/"+salary, output);
			jedis.expire(path+"/"+salary, 10);
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);
		return output;
	}
	
	
	@GET
	@Path("/workers/get/filter/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String filterWorkersXml(@QueryParam("salary") int salary) throws IOException {
		String path="/resurces/workers/get/filter";
		String pathXml="/resurces/workers/get/filter/xml/"+salary;
String output= jedis.get(path+salary);
		
		if (output==null){
			output=getResurceFromDW(path,"salary",""+salary);
			System.out.println("Output from Server .... \n");
			output=xs.listToXml(jsonToList(output));
			jedis.set(pathXml, output);
			jedis.expire(pathXml, 10);
		}else System.out.println("Output from Redis .... \n");
		
		System.out.println(output);
		return output;
	}
	
	
	
	
	
	
	@GET
	@Path("/workers/get/sort/asc/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String sortWorkersAsc() throws IOException {
		String path="/resurces/workers/get/sort/asc";
String output= jedis.get(path);

		if (output==null){
			output=getResurceFromDW(path);
			System.out.println("Output from Server .... \n");
			
			output=xs.listToXml(jsonToList(output));
			jedis.set(path, output);
			jedis.expire(path, 10);
		}else System.out.println("Output from Redis .... \n");
		
		
		
		System.out.println(output);
		return output;

	}
 @GET
 @Path("/workers/get/sort/asc/json")
 @Produces(MediaType.APPLICATION_JSON)
 public String sortWorkersAscJson() throws IOException {
  String path="/resurces/workers/get/sort/asc";
String output= jedis.get(path);

  if (output==null){
   output=getResurceFromDW(path);
   System.out.println("Output from Server .... \n");
   
  
   jedis.set(path+"/json", output);
   jedis.expire(path+"/json", 10);
  }else System.out.println("Output from Redis .... \n");
  
  
  
  System.out.println(output);
  return output;

 }
	
	@GET
	@Path("/workers/get/sort/desc/xml")
	@Produces(MediaType.APPLICATION_XML)
	public String sortWorkersDesc() throws IOException {
		String path="/resurces/workers/get/sort/desc";
String output= jedis.get(path);
		
		if (output==null){
			output=getResurceFromDW(path);
			System.out.println("Output from Server .... \n");
			output=xs.listToXml(jsonToList(output));
			jedis.set(path, output);
			jedis.expire(path, 10);
		}else System.out.println("Output from Redis .... \n");
		
	
		
		System.out.println(output);
		return output;

	}
	
	
	
	
public String getResurceFromDW(String path,String paramName,String param)
{
	int thisport = port + balancing();
	String output;
	Client client = Client.create();
	WebResource webResource = client.resource("http://localhost:" + thisport +path);
	ClientResponse response = webResource.queryParam(paramName, param).accept("application/json").get(ClientResponse.class);
	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}

	 output = response.getEntity(String.class);
		
	return output;
}
public String getResurceFromDW(String path)
{
	int thisport = port + balancing();
	String output;
	Client client = Client.create();
	WebResource webResource = client.resource("http://localhost:" + thisport + path);
	ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	}

	 output = response.getEntity(String.class);
		
	return output;
}
	public int balancing() {
		System.out.println("Balancing .... \n");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2);
		return randomInt;

	}
	
	public ArrayList<Worker> jsonToList(String json) {
		java.lang.reflect.Type type = new TypeToken<List<Worker>>() {
		}.getType();

		ArrayList<Worker> workers = gson.fromJson(json, (java.lang.reflect.Type) type);

		return workers;

	}
}

