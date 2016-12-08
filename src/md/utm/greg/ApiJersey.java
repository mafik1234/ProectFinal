package md.utm.greg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("api")
public class ApiJersey {
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "\n <h1>This is Grigore's serverr<h1>";
    }
 
}