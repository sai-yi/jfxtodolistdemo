package zero.saiyi.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zero.saiyi.todolist.model.TodoData;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainwindow"),700,500);
        stage.setTitle("My todo List app");
        stage.setScene(scene);
        stage.show();
    }
 
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		//super.stop();
		try {
			TodoData.getInstance().saveData();
		}catch(IOException ioe) {
			System.out.println(ioe);
		}
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		//super.init();
		try {
			TodoData.getInstance().loadData();
		}catch(IOException ioe) {
			System.out.println(ioe);
		}
		
		
	}

}