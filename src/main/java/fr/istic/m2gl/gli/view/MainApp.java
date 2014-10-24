package fr.istic.m2gl.gli.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainApp extends Application {

	public TileView tile1;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Group root = new Group();
    	Scene scene = new Scene(root,380,350,Color.BEIGE);
    	tile1 = new TileView();
    	TileView tile2 = new TileView();
    	TileView tile3 = new TileView();
    	tile1.setTranslateX(0);
    	tile2.setTranslateX(90);
    	tile3.setTranslateX(180);
    	root.getChildren().add(tile1);
        root.getChildren().add(tile2);
        root.getChildren().add(tile3);
        
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
