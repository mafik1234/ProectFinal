package md.utm.DW;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;


public class DataWhereHouseService extends Thread{
	static int port;
	private String workerColection;
	DataWhereHouseService(int port){
		this.port=port;
	}
	
	public void run() {

		 System.out.println("Starting Crunchify's Embedded Jersey HTTPServer... PORT "+port+" \n");
	     try{ 
		 HttpServer crunchifyHTTPServer = createHttpServer();
	        crunchifyHTTPServer.start();
	     }catch (IOException e) {

				e.printStackTrace();
			}
	        System.out.println(String.format("\nJersey Application Server started with WADL available at " + "%sapplication.wadl\n", getCrunchifyURI()));
	        System.out.println("Started Crunchify's Embedded Jersey HTTPServer Successfully !!!");
	    }
	 
	        private static HttpServer createHttpServer() throws IOException {
	        ResourceConfig crunchifyResourceConfig = new PackagesResourceConfig("md.utm.DW");
	        return HttpServerFactory.create(getCrunchifyURI(), crunchifyResourceConfig);
	    }
	 
	    private static URI getCrunchifyURI() {
	        return UriBuilder.fromUri("http://" + crunchifyGetHostName() + "/").port(port).build();
	    }
	 
	    private static String crunchifyGetHostName() {
	        String hostName = "localhost";
	        try {
	            hostName = InetAddress.getLocalHost().getCanonicalHostName();
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        }
	        return hostName;
	    }
	
	    public void populateData() throws IOException {

			
			workerColection = new String(Files.readAllBytes(Paths.get("col0.txt")));

		}
	
}
