package aiss.model;

public class Actor {

	private String id;
	private String name;
	private String nationality;
	private String age;

	public Actor() {
	}

	/**
	*
	* @param nationality
	* @param name
	* @param id
	* @param age
	*/
	public Actor(String id, String name, String nationality, String age) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.age = age;
	}
	
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
	
	public String getNationality() {
	return nationality;
	}
	
	public void setNationality(String nationality) {
	this.nationality = nationality;
	}
	
	public String getAge() {
	return age;
	}
	
	public void setAge(String age) {
	this.age = age;
	}
}
