package fr.istic.m2gl.gli.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import fr.istic.m2gl.gli.controller.Controller;


public class MainApp extends Application {

	public BoardView boardView;
	
    @Override
    public void start(Stage primaryStage) throws Exception {

    	Group root = new Group();
    	Scene scene = new Scene(root,380,350,Color.BEIGE);
    	
    	boardView = new BoardView();
    	new Controller(boardView);
        root.getChildren().add(boardView);
        
        primaryStage.setTitle("512");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
