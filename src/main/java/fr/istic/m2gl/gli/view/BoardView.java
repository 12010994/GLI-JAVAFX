package fr.istic.m2gl.gli.view;

import fr.istic.m2gl.gli.model.Board;
import fr.istic.m2gl.gli.model.BoardImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The Class BoardView.
 */
public class BoardView {

	/** The JavaFX scene. */
	public Scene scene;
	
	/** The JavaFX root. */
	public Group root;
	
	/** The board impl. The view know the model to collect data to update*/
	public Board boardImpl;

	/** The JavaFX message to print */
	private Text msg;

	/** The game state. */
	private boolean finish;

	/** The temporary tileView */
	public TileView tileTmp;


	/**
	 * Instantiates a new board view.
	 */
	public BoardView(){
		BoardNew();
	}

	public void BoardNew(){
		root = new Group();
		scene = new Scene(root,370,370, Color.web("0xbbada0"));

		root.getChildren().clear();

		//création de la board avec 4 cases
		boardImpl = new BoardImpl(4);
		finish = false;
		printBoard();
	}

	/**
	 * affiche la board.
	 */
	public void printBoard(){
		//on supprime l'ensemble des enfants de la précédente version
		root.getChildren().clear();

		//sauvegarde du rank max pour savoir si jeu fini
		int maxRank = 0;
		int nb = 0;
		String result = "";
		//coordonnées x pour le carré 
		int valueX = 10;
		//parcours de chaque case de la board
		for(int i =1 ; i<= boardImpl.getSideSizeInSquares(); i++ ){
			int valueY = 10;
			for( int y = 1; y <= boardImpl.getSideSizeInSquares(); y++){

				//test si on a un pion a afficher sinon case vide
				if(boardImpl.getTile(y, i) != null){
					int rank = boardImpl.getTile(y, i).getRank();
					tileTmp = new TileView(rank);
					if(rank > maxRank){
						maxRank = rank;
					}
					nb ++ ;
				}else{
					tileTmp = new TileView();
				}
				root.getChildren().add(tileTmp);
				tileTmp.setTranslateX(valueX);
				tileTmp.setTranslateY(valueY);
				valueY += 90 ;
			}
			result = result + "\n";
			valueX += 90;
			//gagné
			if(maxRank == 9){
				finish = true;
				WinORGameOver(true);
			}
		}
		if(nb == 16){
			//Perdu
			if(boardImpl.gameOver() == false){
				System.out.println("GAME OVER");
				WinORGameOver(false);
			}
		}

	}
	
	/**
	 * interface game over ou Bravo.
	 * @param game the game
	 */
	public void WinORGameOver(boolean game){
		root.getChildren().clear();
		scene.setFill(Color.web("0xccc0b4"));
		if(game){
			msg = new Text("Bravo");
			msg.setX(100);
			msg.setY(100);

		}else{
			msg = new Text("Game Over");
			msg.setX(50);
			msg.setY(100);
		}
		msg.setFont(new Font(50));

		root.getChildren().add(msg);

	}

	/**
	 * Checks if is finish.
	 * @return true, if is finish
	 */
	public boolean isFinish() {
		return finish;
	}

}
