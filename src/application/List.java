package application;

import java.util.Date;

public class List<T extends Comparable<T>> implements Listable<T> {

	T[] list;   //create an array
	int size = 0;   //create an integer to define the size

	public List(int size) {  
		list = (T[]) new Comparable[size];  //set the size of array the size entered
	}

	@Override
	public boolean Add(T data) {    //this method is to add data to the array
		int index = search(data);  //search about the data , if is already exist do not add it
		if (index == -1) {
			if (size < list.length) {
				list[size++] = data;
				return true;
			} else {           //if the array full double its size and then add
				resize();
				list[size++] = data;
				return true;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean delete(T data) {  //this method is to delete data from the array
		int index = search(data);    //if the data exist delete it
		if (index != -1) {
			for (int i = index + 1; i < size; i++) {
				list[i - 1] = list[i];
			}
			size--;
			return true;
		}
		return false;
	}

	@Override 
	public int search(T data) {  //this method is to search about data and return the index for it
		for (int i = 0; i < size; i++) {
			if (data.compareTo(list[i]) == 0) {
				return i;
			}
		}
		return -1;  //if it is not exist return -1
	}

	public void resize() {   //this method is to double the size of array
		T[] newList = (T[]) new Comparable[list.length * 2];  //create a new array with 2*size
		System.arraycopy(list, 0, newList, 0, list.length);  //cope old array to new array
		list = newList;  //let original array equals the new array
	}

	@Override 
	public T get(int i) {   //this method is to return the data according to index
		if(i>=0 && i<size)   
			return list[i];
		
		return null;
	}

}
