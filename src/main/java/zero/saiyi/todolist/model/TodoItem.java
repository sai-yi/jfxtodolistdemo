package zero.saiyi.todolist.model;

import java.time.LocalDate;

public class TodoItem {
	private String title;
	private String details;
	private LocalDate deadLine;
	public TodoItem(String title, String description, LocalDate deadLine) {
		super();
		this.title = title;
		this.details = description;
		this.deadLine = deadLine;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String description) {
		this.details = description;
	}
	public LocalDate getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(LocalDate deadLine) {
		this.deadLine = deadLine;
	}
	@Override
	public String toString() {
		return title;
	}
	
	
}
