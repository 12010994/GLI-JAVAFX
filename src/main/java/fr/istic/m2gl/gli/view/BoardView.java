package fr.istic.m2gl.gli.view;

import javafx.scene.Parent;

public class BoardView extends Parent{
	
	
	public TileView tile1;
	public TileView tile2;
	public TileView tile3;
	public TileView tile4;
	public TileView tile5;
	public TileView tile6;
	public TileView tile7;
	public TileView tile8;
	
	public BoardView(){
		tile1 = new TileView();
		tile1.setTranslateX(0);
		tile2 = new TileView();
		tile2.setTranslateX(90);
		tile3 = new TileView();
		tile3.setTranslateX(180);
		
		this.getChildren().add(tile1);
		this.getChildren().add(tile2);
		this.getChildren().add(tile3);
	}

}
