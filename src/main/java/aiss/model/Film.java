package aiss.model;

import java.util.List;

public class Film {
	private String id;
	private String name;
	private String category;
	private Integer year;
	private List<Actor> cast = null;
	
	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getCategory() {
	return category;
	}

	public void setCategory(String category) {
	this.category = category;
	}

	public Integer getYear() {
	return year;
	}

	public void setYear(Integer year) {
	this.year = year;
	}

	public List<Actor> getCast() {
	return cast;
	}

	public void setCast(List<Actor> cast) {
	this.cast = cast;
	}
}
