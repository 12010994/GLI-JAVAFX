package fr.istic.m2gl.gli.model;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;


public class BoardImpl implements Board {

	private final int sideSizeInSquares;
	private Direction directionToPackInto;

	public BoardImpl(int sideSizeInSquares) {
		if (sideSizeInSquares <= 1) {
			throw new IllegalArgumentException("sideSizeInSquares");
		}
		this.sideSizeInSquares = sideSizeInSquares;
		currentBoard = new Tile[sideSizeInSquares][sideSizeInSquares];
		nextBoard = new Tile[sideSizeInSquares][sideSizeInSquares];

		//Ajout de 2 pions a la création de la board
		RandomTile();
		RandomTile();

		nextBoard = currentBoard;
	}

	@Override
	public int getSideSizeInSquares() {
		return this.sideSizeInSquares;
	}


	private Tile[][] currentBoard;
	private Tile[][] nextBoard;

	/**
	 * Return the tile at a given coordinate, or null if none exists there.
	 *
	 * @param lineNumber   must be >=1 and <= getSideSizeInSquares()
	 * @param columnNumber must be >=1 and <= getSideSizeInSquares()
	 * @return a tile or null if none
	 * @throws IllegalArgumentException if parameters are out of board's bounds
	 */
	@Override
	public Tile getTile(int lineNumber, int columnNumber) {
		return currentBoard[lineNumber - 1][columnNumber - 1];
	}

	/**
	 * Apply the only game action: packing tiles
	 * @param direction  where to push the tiles
	 */
	@Override
	public void packIntoDirection(Direction direction) {
		this.directionToPackInto = direction;
		for (int i = 1; i <= sideSizeInSquares; i++) {
			packLine(i);
		}

	}

	/**
	 * Validate the step effects
	 * NOTE: do we need this in the interface?
	 */
	@Override
	public void commit() {
		currentBoard = nextBoard;  
		nextBoard = new Tile[sideSizeInSquares][sideSizeInSquares];
	}

	private void packLine(int lineNumber) {
		/*
		 * Scan the current board line looking for two consecutive tiles
		 * with the same rank
		 * When this case is encountered, write a single tile with rank+1
		 * Otherwise just copy the tile (in practice packing it in the nex board)
		 * Remember that indices are 1-based in this code
		 * Conversion to Java arrays indices is done in computeLineIndex and computeColumnIndex
		 */

		int readIndex = 1; // Position of the tile to be read
		int writeIndex = 0; // Position of the last tile written
		while (readIndex <= sideSizeInSquares) {
			// Find next tile
			while ((readIndex <= sideSizeInSquares)
					&& (readTile(currentBoard, lineNumber, readIndex) == null)) {
				readIndex++;
			}
			if (readIndex > sideSizeInSquares) {
				break; // Done with the line
			}
			// Try to merge with previous tile
			if ((writeIndex > 0) &&
					(readTile(nextBoard, lineNumber, writeIndex).getRank()
							== readTile(currentBoard, lineNumber, readIndex).getRank())) {
				// Merge previously written tile and currently read one
				readTile(nextBoard, lineNumber, writeIndex).incrementRank();
				writeTile(nextBoard, null, lineNumber, readIndex);
			} else {
				// Advance write index and copy currently read tile
				writeIndex++;
				writeTile(nextBoard, readTile(currentBoard, lineNumber, readIndex), lineNumber, writeIndex);
				if(writeIndex != readIndex){
					writeTile(nextBoard, null, lineNumber, readIndex);
				}
			}
			// Done with the current tile read, move forward
			readIndex++;

		}

	}

	/**
	 * Writes a tile into a matrix (board) using indices transformation
	 * @param board       destination
	 * @param tile        what to write at the given coordinates
	 * @param lineIndex   coordinate
	 * @param columnIndex  coordinate
	 */
	private void writeTile(Tile[][] board, Tile tile, int lineIndex, int columnIndex) {
		board[computeLineIndex(lineIndex, columnIndex)][computeColumnIndex(lineIndex, columnIndex)] = tile;
	}

	/**
	 * Returns a tile  from a matrix (board) using indices transformation
	 * @param board      origin
	 * @param lineIndex   coordinate
	 * @param columnIndex  coordinate
	 * @return    tile at the given coordinates or null if no tile there
	 */
	private Tile readTile(Tile[][] board, int lineIndex, int columnIndex) {
		int boardLineIndex = computeLineIndex(lineIndex, columnIndex);
		int boardColumnIndex = computeColumnIndex(lineIndex, columnIndex);
		Tile currentTile = board[boardLineIndex][boardColumnIndex];
		return currentTile;
	}

	/**
	 * Adds a level of indirection in the index computation
	 * In practice provides a rotation/symmetry so that we need
	 * to deal with one packing directionToPackInto only.
	 * This operation also takes care of the conversion from (1..N) board
	 * coordinates to the (0..N-1) Java array coordinates.
	 *
	 * NOTE: <b>NO CHECKS are made on parameter bounds.</b>
	 *
	 * @param lineIndex   must be in [1..sideSizeInSquares]
	 * @param columnIndex must be in [1..sideSizeInSquares]
	 * @return the columnIndex after rotation/symmetry
	 */
	private int computeColumnIndex(int lineIndex, int columnIndex) {
		switch (directionToPackInto) {
		case RIGHT:
			return sideSizeInSquares - columnIndex;     //Symmetry on a vertical axis
		case LEFT:
			return columnIndex - 1;      //
		case TOP:
			return lineIndex - 1;
		case BOTTOM:
			return lineIndex - 1;
		}
		return 0; // NOT REACHED
	}

	/**
	 * Adds a level of indirection in the index computation
	 * In practice provides a rotation/symmetry so that we need
	 * to deal with one packing directionToPackInto only.
	 * This operation also takes care of the conversion from (1..N) board
	 * coordinates to the (0..N-1) Java array coordinates.
	 *
	 * NOTE: <b>NO CHECKS are made on parameter bounds.</b>
	 *
	 * @param lineIndex   must be in [1..sideSizeInSquares]
	 * @param columnIndex must be in [1..sideSizeInSquares]
	 * @return the lineIndex after rotation/symmetry
	 */
	private int computeLineIndex(int lineIndex, int columnIndex) {
		switch (directionToPackInto) {
		case LEFT:
			return lineIndex - 1;
		case RIGHT:
			return lineIndex - 1;
		case BOTTOM:
			return sideSizeInSquares - columnIndex;
		case TOP:
			return columnIndex - 1;
		}
		return 0; // NOT REACHED
	}

	/**
	 * For testing purposes only.
	 * Creates a board configuration using a matrix of ranks
	 *
	 * @param rankMatrix a non null matrix reference, must match board size
	 */
	public void loadBoard(int[][] rankMatrix) {
		for (int i = 0; i < sideSizeInSquares; i++) {
			for (int j = 0; j < sideSizeInSquares; j++) {
				if (rankMatrix[i][j] > 0) {
					currentBoard[i][j] = new TileImpl(rankMatrix[i][j]);
				}
			}
		}
	}

	/**
	 * For testing purposes only.
	 * Writes the ranks of contents of the matrix into a logger
	 *
	 * @param logger  where to write into
	 * @param message the message to write first before writing the contents of the board
	 */
	public void printBoard(Logger logger, String message) {

		logger.info(message);
		for (int i = 0; i < sideSizeInSquares; i++) {
			StringBuffer outputBuffer = new StringBuffer();
			outputBuffer.append(i + 1);
			outputBuffer.append(":{");
			for (int j = 0; j < sideSizeInSquares; j++) {
				if (currentBoard[i][j] != null) {
					outputBuffer.append(currentBoard[i][j].getRank());
				} else {
					outputBuffer.append("0");
				}
			}
			outputBuffer.append("}");
			logger.info(outputBuffer.toString());
		}
	}

	public void RandomTile(){
		Random randomInt = new Random();
		Tile tileGenerator = new TileImpl(1);

		Map<Point, Tile> availableCase =  getFreeTiles();

		if(!availableCase.isEmpty()){
			LinkedList list = new LinkedList(availableCase.keySet());
			Collections.shuffle(list);
			Point coordToInsertTile = (Point) list.get(0);
			this.currentBoard[coordToInsertTile.x][coordToInsertTile.y] = tileGenerator;
		}
	}

	public Map getFreeTiles(){

		// Map qui permet de stocker les emplacements disponibles pour en suite générer une Tile
		Map<Point, Tile> myMap =  new HashMap<Point, Tile>();

		for(int line = 0; line < sideSizeInSquares; line++){
			for(int col = 0; col < sideSizeInSquares; col++){
				// Si dans cette case je n'ai pas de Tile, je l'ajoute dans la Map des dispos
				if(currentBoard[line][col] == null){
					Point key = new Point(line, col);
					myMap.put(key, new TileImpl(1));
				}
			}
		}
		return myMap;
	}

	@Override
	public String printBoard(){
		if(currentBoard.length == 0){
			System.out.println("erreur");
		}
		String result= "";
		for (int i =0; i<currentBoard.length; i++){
			for (int y = 0; y < currentBoard.length; y++){
				if(currentBoard[i][y] != null){
					result = result + ("  " + currentBoard[i][y].getRank()+ "  ");
				}else{
					result = result + (currentBoard[i][y]+ " ");
				}
			}
			result = result + "\n";
		}
		return result;
	}

	public void move(Direction direction){
		
		int[][] old = new int[sideSizeInSquares][sideSizeInSquares];
		
		int[][] newTab = new int[sideSizeInSquares][sideSizeInSquares];

		//stockage avant mouvement
		for (int i =0; i<currentBoard.length; i++){
			for (int y = 0; y < currentBoard.length; y++){
				if(currentBoard[i][y] != null){
					old[i][y] = currentBoard[i][y].getRank();
				}else{
					old[i][y] = 0;
				}
			}
		}
		//deplacement
		packIntoDirection(direction);

		//stockage après mouvement
		for (int i =0; i<currentBoard.length; i++){
			for (int y = 0; y < currentBoard.length; y++){
				if(currentBoard[i][y] != null){
					newTab[i][y] = currentBoard[i][y].getRank();
				}else{
					newTab[i][y] = 0;
				}
			}
		}
		//test si les 2 tab sont équivalentes 
		if(!Arrays.deepEquals(old, newTab)){
			RandomTile();
		}


	}
	
	//si plus aucun déplacement de possible
	public boolean gameOver(){
		for(int i =0; i<sideSizeInSquares-1; i++){
			for(int y =0; y<sideSizeInSquares-1; y++){
				if( i == sideSizeInSquares){
					if(currentBoard[i][y].getRank() == currentBoard[i][y+1].getRank())
						return(true);
				}else{
					if(currentBoard[i][y].getRank() == currentBoard[i][y+1].getRank() || currentBoard[i][y].getRank() == currentBoard[i+1][y].getRank())
						return(true);	
				}
			}
		}
		return false;
	}

}
