package fr.istic.m2gl.gli.view;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TileView extends Parent {

	private Rectangle tile;
	public Text value;

	public TileView(String val){
		
		tile = new Rectangle();
		value = new Text(val);

		tile.setWidth(80);
		tile.setHeight(80);
		tile.setArcWidth(20);
		tile.setArcHeight(20);
		tile.setFill(
				new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
						new Stop[] {
						new Stop(0, Color.web("orange")),
						new Stop(1, Color.web("yellow"))
				}
						)
				);
		value.setFont(new Font(10));
		value.setFill(Color.BLACK);
		value.setX(30);
		value.setY(40);
		this.getChildren().add(tile);
		this.getChildren().add(value);
	}

	public void setValue(String value) {
		
		this.value.setText(value);
	}
	
}
