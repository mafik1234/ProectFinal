package tools;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import collections.Worker;

public class JsonTool {
	Gson gson = new Gson();
	public ArrayList<Worker> jsonToList(String json) {
		java.lang.reflect.Type type = new TypeToken<List<Worker>>() {
		}.getType();

		ArrayList<Worker> workers = gson.fromJson(json, (java.lang.reflect.Type) type);

		return workers;

	}
}
