package md.utm.DW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/resurces")
public class ApiJersey {
	static Gson gson = new Gson();
	

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

		MongoDB md= new MongoDB();
		
		String workerColection = gson.toJson(md.getFromDB("workers"));
	
		return workerColection;

	}
}