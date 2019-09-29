package zero.saiyi.todolist;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import zero.saiyi.todolist.model.TodoData;
import zero.saiyi.todolist.model.TodoItem;

public class DialogController {

	@FXML
	private TextField shortDescription;
	@FXML
	private TextArea tododetails;
	@FXML
	private DatePicker deadlineDate;
	
	public TodoItem pressedOK() {
		String shortDesc=shortDescription.getText().trim();
		String details=tododetails.getText().trim();
		LocalDate deadLine=deadlineDate.getValue();
		TodoItem newItem=new TodoItem(shortDesc, details, deadLine);
		TodoData.getInstance().addNewTodoDate(newItem);
		return newItem;
	}
}
