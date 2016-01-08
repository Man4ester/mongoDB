package it.bismark.central.models;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public static String TABLE_NAME = "persons";

	private int id;

	private String name;

	private List<String> phones = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

}
