package md.utm.DW;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import collections.Worker;
import tools.Command;

@Path("/resurces")
public class JerseyDW {
	static Gson gson = new Gson();
	Command cmd = new Command();
	MongoDB md = new MongoDB();
	// ArrayList<Worker> listWorkers = md.getFromDB("workers");

	@GET
	@Path("/workers/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkers() {

		String workerColection = gson.toJson(md.getFromDB("workers"));

		return Response.status(200).entity(workerColection).build();

	}

	@GET
	@Path("/workers/get/filter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterWorkers(@QueryParam("salary") int salary) {

		// System.out.println("filter"+salary+listWorkers.toString());

		String workerColection = gson.toJson(cmd.filter(salary, md.getFromDB("workers")));
		return Response.status(200).entity(workerColection).build();

	}

	@GET
	@Path("/workers/get/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sortWorkers() {

		// System.out.println("filter"+salary+listWorkers.toString());

		String workerColection = gson.toJson(cmd.sort(md.getFromDB("workers")));
		return Response.status(200).entity(workerColection).build();

	}

	@POST
	@Path("/workers/post")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postWorkers(@FormParam("surename") String surename, @FormParam("name") String name,
			@FormParam("salary") int salary) {

		Worker wk = new Worker(name, surename, salary);
		boolean status = md.insertToDB("workers", wk);

		if (status) {
			return Response.status(201).entity("Employee Post successfully !!").build();
		} else
			return Response.status(204).entity("Employee Post unsuccessfully !!").build();

	}

}