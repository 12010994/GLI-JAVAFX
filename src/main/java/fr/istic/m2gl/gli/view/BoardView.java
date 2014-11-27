package fr.istic.m2gl.gli.view;


import fr.istic.m2gl.gli.model.Board;
import fr.istic.m2gl.gli.model.BoardImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardView {
	
	public Scene scene;
	public Group root;
	public Board boardImpl;
	
	private Text msg;
	private Rectangle btn;
	
	private Text msgBtn;
	
	private boolean finish;
	
	public TileView tileTmp;
	
		
	public BoardView(){
		BoardNew();
	}
	
	public void BoardNew(){
		root = new Group();
		scene = new Scene(root,370,370, Color.web("0xbbada0"));
		
		root.getChildren().clear();
		
		//création de la board avec 4 cases
		boardImpl = new BoardImpl(3);
		finish = false;
		printBoard();
	}
	
	public void printBoard(){
		//on supprime l'ensemble des enfants de la précédente version
		root.getChildren().clear();
		
		//sauvegarde du rank max pour savoir si jeu fini
		int maxRank = 0;
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
			if(maxRank == 9){
				finish = true;
				WinORGameOver(true);
			}
		}
	}
	
	public void WinORGameOver(boolean game){
		btn = new Rectangle();
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
		msgBtn = new Text("RECOMMENCER");
		msgBtn.setFont(new Font(25));
		msgBtn.setFill(Color.WHITE);
		msgBtn.setX(85);
		msgBtn.setY(250);
		
		btn.setWidth(240);
		btn.setHeight(80);
		btn.setX(70);
		btn.setY(200);
		btn.setArcWidth(20);
		btn.setArcHeight(20);
		btn.setFill(Color.web("0x776e65"));
		root.getChildren().add(btn);
		root.getChildren().add(msg);
		root.getChildren().add(msgBtn);
		
	}
	
	public boolean isFinish() {
		return finish;
	}
	
	public void hasCliked(MouseEvent e){
		if( btn.contains(e.getX(), e.getY())){
			System.out.println("oui");
			BoardNew();
		}else{
			System.out.println("non");
		}
		
	}

		
}
