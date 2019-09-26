package songlistApp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("songlist.fxml"));
    	
    	Parent root = (Parent)loader.load();
    	
        Controller controller = loader.getController();
		controller.start(primaryStage);

		primaryStage.setTitle("Your Song List");
        primaryStage.setScene(new Scene(root, 552, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
