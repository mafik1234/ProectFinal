package md.utm.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class StartProxyServer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println("Starting Jersey HTTPServer...\n");
	     try{ 
		 HttpServer crunchifyHTTPServer = createHttpServer();
	        crunchifyHTTPServer.start();
	     }catch (IOException e) {

				e.printStackTrace();
			}
	        System.out.println(String.format("\nJersey Application Server  at " + "%sapplication.wadl\n", getCrunchifyURI()));
	        System.out.println("Started Proxy Server Successfully !!!");
	    }
	 
	        private static HttpServer createHttpServer() throws IOException {
	        ResourceConfig crunchifyResourceConfig = new PackagesResourceConfig("md.utm.proxy");
		        return HttpServerFactory.create(getCrunchifyURI(), crunchifyResourceConfig);
	    }
	 
	    private static URI getCrunchifyURI() {
	        return UriBuilder.fromUri("http://" + crunchifyGetHostName() + "/").port(6005).build();
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
		private String workerColection;
		public void populateData() throws IOException {

			
			workerColection = new String(Files.readAllBytes(Paths.get("col0.txt")));

		}
}
