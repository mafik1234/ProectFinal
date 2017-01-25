package tools;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import collections.Worker;
import collections.WorkerList;



public class XstreamTool {

	
	public ArrayList<Worker> getColection(String colName) throws IOException{
		
		 XStream xstream = new XStream();
		    xstream.alias("worker", Worker.class);
		    xstream.alias("workers", WorkerList.class);
		    xstream.addImplicitCollection(WorkerList.class, "list");

		    WorkerList list = new WorkerList();
		   
		    String content = new String(Files.readAllBytes(Paths.get(colName)));
		
		    WorkerList pList = (WorkerList)xstream.fromXML(content); 
		  
		return pList.list;
		
	}
	
	public ArrayList<Worker> xmlToList(String xml) throws IOException{
		
		 XStream xstream = new XStream();
		    xstream.alias("worker", Worker.class);
		    xstream.alias("workers", WorkerList.class);
		    xstream.addImplicitCollection(WorkerList.class, "list");

		    WorkerList list = new WorkerList();
		   
		    
		
		    WorkerList pList = (WorkerList)xstream.fromXML(xml); 
		  
		return pList.list;
		
	}
	
	public String listToXml(ArrayList<Worker> listWorkers) throws IOException{
		
		 XStream xstream = new XStream();
		    xstream.alias("worker", Worker.class);
		    xstream.alias("workers", WorkerList.class);
		    xstream.addImplicitCollection(WorkerList.class, "list");

		    WorkerList wlist = new WorkerList();
		   
		    wlist.list.addAll(listWorkers);
		  

		    String xml = xstream.toXML(wlist);
		  
		return xml;
		
	}
	
}