package fr.istic.m2gl.gli.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import fr.istic.m2gl.gli.model.*;
import fr.istic.m2gl.gli.model.Board.Direction;
import fr.istic.m2gl.gli.view.BoardView;

public class Controller{

	public Controller(final BoardView boardView){	

		boardView.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke){
				Board boardImpl = boardView.boardImpl;
				if(!boardView.isFinish()){
					switch (ke.getCode()) {
						case RIGHT: boardImpl.move(Direction.RIGHT);break; 
						case LEFT: boardImpl.move(Direction.LEFT); break;
						case UP: boardImpl.move(Direction.TOP);break;
						case DOWN: boardImpl.move(Direction.BOTTOM);break;
						default: 
							break;
				}
				}
				boardView.printBoard();
			}
		});

	}
}
