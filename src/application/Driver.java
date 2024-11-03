package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.*;

public class Driver extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Button chooseFile = new Button("choos file and upload data");    //this button is to choose file  
			
			GridPane grid = new GridPane();  //create a GridPane 
			grid.setAlignment(Pos.CENTER);
			grid.setVgap(10);
			grid.setHgap(15);

			Button addbtn = new Button("Add Martyr"); //this button to add martyr
			TextField addtxt = new TextField();   //this textField is to write the information about martyr inside it
			Label addSyntax = new Label("Name,Age,event Location,date of death,Gender\nEnter Age like this: day/month/year"); //this Label is to display the syntax of information of martyr
			Label addStatus = new Label();  //this Label is to display the result of add
             addtxt.setPrefColumnCount(25);
			Button deletebtn = new Button("Delete a Martyr"); //this button to delete martyr
			TextField deletetxt = new TextField();  //this textField is to write the name of martyr inside it
			Label deleteSyntax = new Label("Enter the name"); 
			Label deleteStatus = new Label(); //this Label is to display the result of delete

			Button searchbtn = new Button("Search about Martyr"); //this button to search about martyr
			TextField searchtxt = new TextField();  //this textField is to write the name of martyr inside it
			Label searchSyntax = new Label("Enter the name");
			Label searchedMartyr = new Label(); //this Label is to display the result of search
               
			ComboBox<String> numOfMartyr = new ComboBox<>(); //this comboBx is to Let the user to choose age/gender/date to display the number of martyrs
			Label number = new Label();       //this Label is to display the number of martyrs  
			TextField determinedNum = new TextField(); //this TextField is to Write the specific age/gender/date
			numOfMartyr.setPromptText("Select age/gender/date"); 
			String[] selected = { "age", "gender", "date" }; 
			ObservableList list = FXCollections.observableArrayList(selected); 
			numOfMartyr.getItems().addAll(list);
			Button numOfMartyrsbtn = new Button("Calculate"); //this button to calculate number of martyrs
			
			
			// Add the nodes to the GridPane
			grid.add(chooseFile, 1, 0);
			
			grid.add(addbtn, 0, 1);
			grid.add(addSyntax, 1, 1);
			grid.add(addtxt, 2, 1);
			grid.add(addStatus, 3, 1);
			
			grid.add(deletebtn, 0, 2);
			grid.add(deleteSyntax, 1, 2);
			grid.add(deletetxt, 2, 2);
			grid.add(deleteStatus, 3, 2);
			
			grid.add(searchbtn, 0, 3);
			grid.add(searchSyntax, 1, 3);
			grid.add(searchtxt, 2, 3);
			grid.add(searchedMartyr, 3, 3);
			
			grid.add(numOfMartyr, 0, 4);
			grid.add(determinedNum, 1, 4);
			grid.add(number, 3, 4);
			grid.add(numOfMartyrsbtn, 2, 4);

			List<Martyr> martyrs = new List<>(16);  //create the array of martyrs to store the martyrs from the file

			
			//Actions
			
			
			//read from file
			chooseFile.setOnAction(e -> {          //this action to Let the user to choose the file
				FileChooser filechooser = new FileChooser();    //create a file chooser
				filechooser.setTitle("Choose file");         //title of file chooser
				filechooser.setInitialDirectory(new File("C:\\Users\\HITECH\\Downloads"));  //the initial directory when the file chooser opened
				filechooser.getExtensionFilters().addAll(new ExtensionFilter("csv files", "*.csv")); //the type of files appears on file chooser
				File selectedFile = filechooser.showOpenDialog(primaryStage);  
				
				if (selectedFile != null) {  //if the selected file not null
					try {
						Scanner sc = new Scanner(selectedFile);            //read the information of martyrs from the file
						int x = 0;
						while (sc.hasNext()) {
							String[] line = sc.nextLine().split(",");
							if (line.length == 5) {        //if the lines contains all information read to store
								if (line[4].charAt(0) == 'F' || line[4].charAt(0) == 'M') {   //if the gender F or M store the martyr
									String[] date = line[3].split("/");
									if (date.length == 3) {   //check the date
										try {
											Date dateOfDeath = new Date(Integer.parseInt(date[2]) - 1900,    //create a date from the information 
													Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
											Martyr martyr = new Martyr(line[0], Integer.parseInt(line[1]), line[2],dateOfDeath, line[4].charAt(0));  //create a martyr from the information
													System.out.println(martyr.toString());
											martyrs.Add(martyr);  //add the martyr to array

										} catch (Exception ex) {

										}
									}
								}
							}
						}
						System.out.println(martyrs.size);
					} catch (FileNotFoundException e1) {

					}
				}
			});

			//Add
			addbtn.setOnAction(e -> {   //this action is to add martyr
				String[] line = addtxt.getText().split(",");   //take the value in the text field
				if (line.length == 5) {           //check if it has all information
					if (line[4].charAt(0) == 'F' || line[4].charAt(0) == 'M') { //check if gender M or F
						String[] date = line[3].split("/");
						if (date.length == 3) {   //check date and create it
							try {
								Date dateOfDeath = new Date(Integer.parseInt(date[2]) - 1900,Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
								Martyr martyr = new Martyr(line[0], Integer.parseInt(line[1]), line[2], dateOfDeath,line[4].charAt(0));
								if (martyrs.Add(martyr)) {   //add the martyr if he/she does not exist
									addStatus.setText("Done,the martyr added");  //display the result of try adding
								} else {
									addStatus.setText(
											"Fail,The martyr is already exist or the information not accurate");
								}
							} catch (Exception ex) {
								addStatus.setText("There is an Error in the information ");
							}
						} else {
							addStatus.setText("Check the date please");
						}

					} else {
						addStatus.setText("check the gender please");
					}
				} else {
					addStatus.setText("Enter all information please");
				}
			});

			//delete
			deletebtn.setOnAction(e -> {  //this action is to delete a martyr from array
				if (deletetxt.getText() != null) {
					Martyr martyr = new Martyr(deletetxt.getText());  //create a martyr using name
					if (martyrs.delete(martyr)) {  //if he/she exists delete it
						deleteStatus.setText("Done,the martyr was deleted");   //display the result of try deleting
					} else {
						deleteStatus.setText("Fail,The martyr does not exist or the information not accurate");
					}
				} else {
					deleteStatus.setText("Enter Name please");
				}
			});

			//search
			searchbtn.setOnAction(e -> {  //this action is to search about a martyr using name
				if (searchtxt.getText() != null) {
					Martyr martyr = new Martyr(searchtxt.getText());   //create a martyr using name
					if (martyrs.search(martyr) != -1) {  //if the martyr exists display its information
						searchedMartyr.setText(martyrs.get(martyrs.search(martyr)).toString());
					} else {
						searchedMartyr.setText("The martyr does not exist");
					}
				} else {
					searchedMartyr.setText("Enter Name please");
				}
			});

			//calculate number of martyrs
			numOfMartyrsbtn.setOnAction(e -> {   //this action is to calculate number of martyrs according to age/gender/date
				if (numOfMartyr.getSelectionModel().getSelectedIndex() == 0) {  //if the selection in comboBox is age display according age
					try {
						int age = Integer.parseInt(determinedNum.getText());
						if (age >= 0)
							number.setText("" + numberOfMartyr(age, martyrs));  //display the number of martyrs according to the age
						else
							number.setText("Enter an positive age please");
					} catch (Exception ex) {
						number.setText("Enter an integer age please");
					}
				} else if (numOfMartyr.getSelectionModel().getSelectedIndex() == 1) { //if the selection in comboBox is age display according gender

					try {
						if (determinedNum.getText().length() == 1) {
							char gender = determinedNum.getText().charAt(0);  
							if (gender == 'F' || gender == 'M') {
								number.setText("" + numberOfMartyr(gender, martyrs));  //display the number of martyrs according to the gender
							} else {
								number.setText("Enetr M/F please");
							}
						} else {
							number.setText("Enetr M/F please");
						}

					} catch (Exception ex) {
						number.setText("Enter gender please");
					}
				} else {   //if the selection in comboBox is age display according date
					String[] date = determinedNum.getText().split("/");
					if (date.length == 3) {
						try {
							Date dateOfDeath = new Date(Integer.parseInt(date[2]) - 1900,Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
						   number.setText(""+numberOfMartyr(dateOfDeath,martyrs));  //display the number of martyrs according to the date

						} catch (Exception ex) {
							addStatus.setText("Enter the date: day/month/year");
						}
					}else {
						addStatus.setText("Enter the date: day/month/year");
					}
				}
			});

			 //put the GridPane in the Scene and put the Scene in the Stage
			Scene scene = new Scene(grid, 1550, 800);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static int numberOfMartyr(int age, List<Martyr> list) {  //this method is to calculate number of martyrs in the specific age
		int count = 0;  //create a counter
		for (int i = 0; i < list.size; i++) {   //check the age of all martyrs
			if (list.get(i).getAge() == age) {  //if the age of this martyr equals the specific age increment the counter
				count++;
			}
		}

		return count;   //return the counter
	}

	public static int numberOfMartyr(char gender, List<Martyr> list) {  //this method is to calculate number of martyrs in the specific gender
		int count = 0;  //create a counter
		for (int i = 0; i < list.size; i++) {   //check the gender of all martyrs
			if (list.get(i).getGender() == gender) {   //if the gender of this martyr equals the specific gender increment the counter
				count++;
			}
		}

		return count;  //return the counter
	}

	public static int numberOfMartyr(Date date, List<Martyr> list) { //this method is to calculate number of martyrs in the specific date
		int count = 0;  //create a counter
		for (int i = 0; i < list.size; i++) { //check the date of all martyrs
			if (list.get(i).getDateOfDeath().equals(date)) {    //if the date of this martyr equals the specific date increment the counter
				count++;
			}
		}
		
		return count; //return the counter
	}

	public static void main(String[] args) {
		launch(args);
	}
}
