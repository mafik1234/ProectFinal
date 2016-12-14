package md.utm.DW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


import collections.Worker;

@Path("/resurces")
public class JerseyDW {
	static Gson gson = new Gson();

	MongoDB md= new MongoDB();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTrackInJSON(@PathParam("username") String userName) {

		String s="{\"nodeId\":\"lalala\"}";
		try {
		String	workerColection = new String(Files.readAllBytes(Paths.get("col0.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@GET
	@Path("/workers/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWorkers() {


		
		String workerColection = gson.toJson(md.getFromDB("workers"));
	
		return workerColection;

	}
	@POST
	@Path("/workers/post")
	@Produces(MediaType.APPLICATION_JSON)
	public Response   putWorkers(@FormParam("name") String name, @FormParam("surename") String surename, @FormParam("salary") int salary ) {

	
		Worker wk = new Worker(name, surename, salary);
		md.insertToDB("workers", wk);
		String workerColection = gson.toJson(md.getFromDB("workers"));
		System.out.println("put");
		
		
		return Response.status(202).entity("Employee deleted successfully !!").build();
		
	}
}