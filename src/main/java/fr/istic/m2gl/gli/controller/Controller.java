package fr.istic.m2gl.gli.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import fr.istic.m2gl.gli.model.*;
import fr.istic.m2gl.gli.model.Board.Direction;
import fr.istic.m2gl.gli.view.BoardView;
import fr.istic.m2gl.gli.view.TileView;

public class Controller{

	public Controller(BoardView boardView){
		Board BoardImpl = new BoardImpl(4);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.TOP);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.TOP);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.TOP);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.TOP);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.RIGHT);
		System.out.println("\n"+BoardImpl.printBoard());
		BoardImpl.commit(Direction.LEFT);
		System.out.println("\n"+BoardImpl.printBoard());




		boardView.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke){
				System.out.println("Key pressed");
				boardView.tile1.setTranslateY(85);
				boardView.tile1.setValue("CHANGED");
			}
		});

		boardView.scene.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent ke){
				System.out.println("Mouse pressed");
			}
		});
	}

}
