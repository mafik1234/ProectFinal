package md.utm.DW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")
public class ApiJersey {

	private String workerColection;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTrackInJSON(@PathParam("username") String userName) {

		String s="{\"nodeId\":\"lalala\"}";
		try {
			workerColection = new String(Files.readAllBytes(Paths.get("col0.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workerColection;

	}
}