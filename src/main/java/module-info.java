module zero.saiyi.todolist {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens zero.saiyi.todolist to javafx.fxml;
    exports zero.saiyi.todolist;
}