package application;
import java.util.*;
public class Martyr implements Comparable<Martyr> {
     //create the attributes
	private String name;
	private int age;
	private String eventLocation;
	private Date dateOfDeath;
	private char gender;
	
	
	public Martyr() { //no-argument constructor
		
	}


	public Martyr(String name, int age, String eventLocation, Date dateOfDeath, char gender) {  //constructor with arguments
		
		this.name = name;
		this.age = age;
		this.eventLocation = eventLocation;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
	}

	

	public Martyr(String name) {  //constructor with name to make the delete and search easy
		 
		this.name = name;
	}

      //setters and getters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEventLocation() {
		return eventLocation;
	}


	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}


	public Date getDateOfDeath() {
		return dateOfDeath;
	}


	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	@Override
	public int compareTo(Martyr o) {        //'compare to' to compare name 
		if(name.equals(o.getName())) {
			return 0;
		}
		return 1;
	}
	
	public int compareTo(int age) {  //'compare to' to compare age
		if(age== this.age) {
			return 0;
		}
		return 1;
	}
	public int compareTo(char gender) {  //'compare to' to compare gender
		if(this.gender == gender) {
			return 0;
		}
		return 1;
	}
	public int compareTo(Date dateOfDeath) {  //'compare to' to compare date
		return this.dateOfDeath.compareTo(dateOfDeath);
	}


	@Override
	public String toString() {  //toString for martyr
		return  name + "," + age + "," + eventLocation + ","
				+ dateOfDeath.getDate()+"/"+(dateOfDeath.getMonth()+1)+"/"+(dateOfDeath.getYear()+1900) + "," + gender ;
	}
	
}
