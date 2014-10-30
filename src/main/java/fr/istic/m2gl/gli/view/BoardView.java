package fr.istic.m2gl.gli.view;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class BoardView{
	
	public Scene scene;
	public Group root;
	
	public TileView tile1;
	public TileView tile2;
	public TileView tile3;
	public TileView tile4;
	public TileView tile5;
	public TileView tile6;
	public TileView tile7;
	public TileView tile8;
	
	public BoardView(){
		
		root = new Group();
		scene = new Scene(root,380,350,Color.BEIGE);
		
		tile1 = new TileView("test");
		tile1.setTranslateX(0);
		
		tile2 = new TileView("0");
		tile2.setTranslateX(90);
		
		tile3 = new TileView("0");
		tile3.setTranslateX(180);
		
		root.getChildren().add(tile1);
		root.getChildren().add(tile2);
		root.getChildren().add(tile3);
	}
	
}
