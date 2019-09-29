module zero.saiyi.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    opens zero.saiyi.todolist to javafx.fxml;
    exports zero.saiyi.todolist;
}