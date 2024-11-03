package application;
import java.util.*;
public interface Listable <T extends Comparable<T>> {  //create an interface 
  //the method that should implements in the subclasses
	public boolean Add(T data);  //add data
	public boolean delete(T data); //delete data
	public int search(T data); //search about data
	public T get(int i);  //return data
	
	
}
