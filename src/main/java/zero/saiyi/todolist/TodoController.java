package zero.saiyi.todolist;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
//import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import zero.saiyi.todolist.model.TodoData;
import zero.saiyi.todolist.model.TodoItem;

public class TodoController {

	private ArrayList<TodoItem> todoList;
	@FXML
	private ListView<TodoItem> todoListView;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private Label labelDate;
	@FXML
	private BorderPane mainWindow;
	public void initialize() {
		
//		todoList = new ArrayList<TodoItem>();
//		todoList.add(new TodoItem("todo One"," First todo blah blah blah blah blah blah ", LocalDate.of(2019, Month.SEPTEMBER,28)));
//		todoList.add(new TodoItem("todo Two"," Second blah blah blah blah blah blah blah blah ", LocalDate.of(2019, Month.SEPTEMBER,29)));
//		todoList.add(new TodoItem("todo Three","Thrid blah blah blah blah blah blah blah blah blah ", LocalDate.of(2019, Month.OCTOBER,5)));
//		
//		todoListView.getItems().addAll(todoList);
//		
//		TodoData.getInstance().setTodoItems(todoList);
		
		
	  //todoListView.getItems().addAll(TodoData.getInstance().getTodoItems());
		todoListView.setItems(TodoData.getInstance().getTodoItems());
	  
	  todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);	
		
		todoListView.getSelectionModel().selectedItemProperty().addListener(new SelectedItemListener());
		
		todoListView.getSelectionModel().selectFirst();
		
	}
//	@FXML
//	public void selectedItemEvents() {
//		TodoItem selectedItem = (TodoItem)todoListView.getSelectionModel().getSelectedItem();
//		//StringBuilder sb = new StringBuilder(selectedItem.getDescription());
//		//sb.append("\n\n\n\n\n");
//		//sb.append("schedule date :: "+selectedItem.getDeadLine().toString());
//		
//		descriptionArea.setText(selectedItem.getDescription());
//		labelDate.setText(selectedItem.getDeadLine().toString());
//		descriptionArea.setEditable(false);
//	}
	public String formatDate(LocalDate date) {

		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd,yyyy");
		return dtFormat.format(date);
	}
	
	public void loadDialog() {
		Dialog<ButtonType> dialog=new Dialog();
		dialog.initOwner(mainWindow.getScene().getWindow());
		dialog.setTitle("New TodoItem");
		dialog.setHeaderText("Create New Todo Item Here...");
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("tododialog.fxml"));
		try {
			
			dialog.getDialogPane().setContent(loader.load());
			//dialog.show();
			
			
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
			ioe.printStackTrace();
		}
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> request=dialog.showAndWait();
		
		if(request.isPresent() && request.get() == ButtonType.OK) {
			System.out.println("pressed OK");
		
			DialogController controller=loader.getController();
			TodoItem item=controller.pressedOK();
		
			//	todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
			todoListView.getSelectionModel().select(item);
		}else {
			System.out.println("pressed Cancel");
		}
	}
	
	class SelectedItemListener implements ChangeListener<TodoItem>{

		@Override
		public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
			if(newValue != null) {
				TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
				descriptionArea.setText(selectedItem.getDetails());
				//labelDate.setText(selectedItem.getDeadLine().toString());
				labelDate.setText(formatDate(selectedItem.getDeadLine()));
			}
		}
		
	}
}
