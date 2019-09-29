package zero.saiyi.todolist.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoData {
	private static TodoData instance = new TodoData();
	private static final String FILE_NAME = "tododata.txt";
	private DateTimeFormatter dateTimeFormatter;
	private ObservableList<TodoItem> todoItems;
	
	private TodoData() {
		dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//todoItems =FXCollections.observableArrayList();
	}

	public ObservableList<TodoItem> getTodoItems() {
		return todoItems;
	}

	public void setTodoItems(ObservableList<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}
	public static TodoData getInstance() {
		return instance;
	}
	
	public void addNewTodoDate(TodoItem newTodo) {
		todoItems.add(newTodo);
	}
	
	public void loadData() throws IOException{
		todoItems =FXCollections.observableArrayList();
		Path filePath=Paths.get(FILE_NAME);
		
		BufferedReader buffReader=Files.newBufferedReader(filePath);
		
		String input;
		try {
			while((input=buffReader.readLine())!= null) {
				String[] dataString=input.split("\t");
				String shortD=dataString[0];
				String detatils=dataString[1];
				String dateString=dataString[2];
				LocalDate date= LocalDate.parse(dateString, dateTimeFormatter);
				TodoItem loadedDate= new TodoItem(shortD, detatils, date);
				
				//add to list
				todoItems.add(loadedDate);				
			}
			buffReader.close();
		}finally {
			if(buffReader != null) {
				buffReader.close();
			}
		}	
		
	}
	
	public void saveData() throws IOException{
		Path filePath=Paths.get(FILE_NAME);
		BufferedWriter buffWriter = Files.newBufferedWriter(filePath);
		try {
			Iterator<TodoItem> it=todoItems.iterator();
			while(it.hasNext()) {
				TodoItem item = it.next();
				buffWriter.write(String.format("%s\t%s\t%s\t",item.getTitle(),item.getDetails(),item.getDeadLine()));
				buffWriter.newLine();				
			}
			buffWriter.close();			
		}finally {
			if(buffWriter != null) {
				buffWriter.close();
			}
		}
	}
	
}
