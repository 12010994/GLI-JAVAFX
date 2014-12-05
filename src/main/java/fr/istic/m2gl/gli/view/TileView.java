package fr.istic.m2gl.gli.view;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class TileView extends Parent {

	private Rectangle tile;
	public Text value;

	/**
	 * constructeur pion avec valeur
	 * @param val
	 */
	public TileView(int val){

		tile = new Rectangle();
		value = new Text(getNumRank(val));
		//tableau de couleur des pions
		Color color[] = 	 {Color.web("0xeee4da"), Color.web("0xede0c8"), Color.web("0xf2b179"), Color.web("0xec8d54"), Color.web("0xf67c5f"), Color.web("0xea5937"), Color.web("0xf3d86b"), Color.web("0xf1d04b"), Color.web("0xf3d86b")};
		//tableau de couleur de la valeur
		Color colorString[]= {Color.web("0x776e65"), Color.web("0x776e65"), Color.web("0xffffff"), Color.web("0xffffff"), Color.web("0xffffff"), Color.web("0xffffff"), Color.web("0xffffff"), Color.web("0xffffff"), Color.web("0xffffff")};
		
		//taille du pion et angle arrondi
		tile.setWidth(80);
		tile.setHeight(80);
		tile.setArcWidth(20);
		tile.setArcHeight(20);
		tile.setFill(color[val-1]);
		
		//ajout de texte avec le chiffre
		value.setFont(new Font(25));
		value.setFill(colorString[val-1]);
		//deplacement de la valeur suivant le nombre de chiffre dans le nombre
		if(val <= 3){
			value.setX(30);
			value.setY(50);
		}else if(val <= 6){
			value.setX(25);
			value.setY(50);
		}else{
			value.setX(15);
			value.setY(50);
		}
		
		this.getChildren().add(tile);
		this.getChildren().add(value);
	}
	
	
	/**
	 * constructeur pion vide
	 */
	public TileView(){
		tile = new Rectangle();
		tile.setWidth(80);
		tile.setHeight(80);
		tile.setArcWidth(20);
		tile.setArcHeight(20);
		tile.setFill(Color.web("0xccc0b4"));
		this.getChildren().add(tile);
	}

	public void setValue(String value) {
		this.value.setText(value);
	}
	
	/**
	 * retourne valeur du pion suivant le rank
	 * @param rank
	 * @return num
	 */
	public String getNumRank(int rank){
		String num;
		switch (rank) {
		case 1: num = "2";break;
		case 2: num = "4"; break;
		case 3: num = "8"; break;
		case 4: num = "16"; break;
		case 5: num = "32"; break;
		case 6: num = "64"; break;
		case 7: num = "128"; break;
		case 8: num = "256"; break;
		case 9: num = "512"; break;
		default: num = "2";
			break;
		}
		return num;
	}

}
