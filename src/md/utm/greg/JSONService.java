package md.utm.greg;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json/{username}")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTrackInJSON(@PathParam("username") String userName) {

		String s="{\"nodeId\":\"lalala\"}";

		return s+userName;

	}
}