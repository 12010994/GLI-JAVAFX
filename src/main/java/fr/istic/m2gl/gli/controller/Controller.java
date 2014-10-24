package fr.istic.m2gl.gli.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import fr.istic.m2gl.gli.model.*;
import fr.istic.m2gl.gli.view.BoardView;

public class Controller{

	public Controller(BoardView boardView){
		Board BoardImpl = new BoardImpl(8);
		BoardImpl.commit();



		boardView.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent ke){
				System.out.println("Key pressed");
			}
		});

		boardView.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent ke){
				System.out.println("Mouse pressed");
			}
		});
	}

}
