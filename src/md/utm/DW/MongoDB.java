package md.utm.DW;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import collections.Worker;

public class MongoDB {
	public MongoDB() {
		db = connectToDB("tes4");
	}

	public static DB db;
	static Gson gson = new Gson();

	public static void main(String args[]) {
	/*	db = connectToDB("test3");
		
		
		getFromDB("workers");*/
	MongoDB md= new MongoDB();
	db = md.connectToDB("tes3");
	Worker wk = new Worker("greg", "bro", 1000);
	md.insertToDB("workers", wk);
		String workerColection = gson.toJson(md.getFromDB("workers"));
		
		System.out.println(workerColection);

	}

	public  DB connectToDB(String nameDB) {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB(nameDB);
			System.out.println("Connect to database successfully");
			return db;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public  boolean insertToDB(String collName, Worker wk) {

		DBCollection coll = db.createCollection(collName, null);

		String json = gson.toJson(wk);

		DBObject dbObject = (DBObject) JSON.parse(json);
try{
		coll.insert(dbObject);
		System.out.println("Document inserted successfully");
		return true;
}
catch(Exception e)
{
	return false;
}
		
	}

	public  ArrayList<Worker> getFromDB(String collName) {
		//db = connectToDB("tes4");
		ArrayList<Worker> workerList = new ArrayList<Worker>();

		DBCollection coll = db.getCollection(collName);

		DBCursor cursor = coll.find();
		int i = 1;

		while (cursor.hasNext()) {
			
			DBObject dbObj = cursor.next();

			String name = (String) dbObj.get("name");
			String surname = (String) dbObj.get("name");
			//int salary = Integer.parseInt((String)dbObj.get("salary"));
			int salary =(Integer)dbObj.get("salary");
			
			Worker wk = new Worker(name, surname, salary);
			workerList.add(wk);

			
		
		}
		String json = gson.toJson(workerList);
		System.out.println(json);
		return workerList;
	}
}