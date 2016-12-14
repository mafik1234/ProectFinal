package tools;

import java.util.ArrayList;
import java.util.stream.Collectors;

import collections.Worker;

public class Command {
public ArrayList<Worker> filter(int salary,ArrayList<Worker>listWorkers ) {
		
		
	
	 listWorkers = (ArrayList<Worker>) listWorkers.stream().filter(item -> item.salary >= salary)
			.collect(Collectors.toList());
	
	
		return listWorkers;

	}



public ArrayList<Worker> sort(ArrayList<Worker>listWorkers) {

listWorkers = (ArrayList<Worker>) listWorkers.stream()
		.sorted((Worker e1, Worker e2) -> Integer.compare(e1.salary, e2.salary)).collect(Collectors.toList());



return listWorkers;

}
}
