package chess;


import chess.pieces.*;

import java.util.HashMap;
import java.util.Map;

import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.King;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;
    
    private Position whiteKingPosition = null;
    private Position blackKingPosition = null;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        whiteKingPosition = new Position("e1");    	
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2")); 
        
        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        blackKingPosition = new Position("e8");
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));  
  
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
    	
    	if (position == null){
    		return null;
    	}
        return positionToPieceMap.get(position);
    }

    
    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    public void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }
    
    /**
     * Method to remove a piece from the board
     * @param position the place of the piece to remove
     */
    public void removePiece(Position position) {
    	positionToPieceMap.remove(position);
    }
    
    /**
     * Returns true if the owner's King will be in check given a move
     * Returns false if the owner's King will not be in check given a move
     * @param playerTurn
     * @return
     */
    public boolean isKingCheck(Player playerTurn, Piece piece, Position initialPosition, Position finalPosition) {

    	boolean kingIsInCheck = false;
    	Piece capturedPiece = null;
    	
    	// Move the piece to the new position to check if the King is in check, this will be reverted at the end
    	this.removePiece(initialPosition);
    	capturedPiece = getPieceAt(finalPosition);    	
    	this.placePiece(piece, finalPosition);

    	Position attackingPiecePosition = null;
    	Piece attackingPiece = null;

    	// Check if the piece being moved is a king, if it is make sure it isn't being moved adjacent to the enemy king
    	// If it's not moving adjacent to enemy king, set the new king position (to be reverted at the end)
    	if (piece instanceof King) {        	
    		
    		int [][] newKingPositions = {
        			{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
        	};

        	for (int posOffset[] : newKingPositions){
        		attackingPiecePosition = Position.getPositionOffset(finalPosition, posOffset[0], posOffset[1]);
        		if (attackingPiecePosition !=null)
        			attackingPiece = getPieceAt(attackingPiecePosition);
            	if ((attackingPiece != null) && (attackingPiece instanceof King)){
            		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
            		return true; 
            	} 
        	}
    		
    		if (playerTurn == Player.White){
    			whiteKingPosition = finalPosition;
    		} else {
    			blackKingPosition = finalPosition;
    		}
    	}    	
    	
    	// Now check all the paths leading to the King to see if it's in Check
    	Position kingPosition = (playerTurn == Player.White) ? whiteKingPosition : blackKingPosition;
    	
    	// Check if a Pawn has an attack path
    	int moveAmount = (playerTurn == Player.White) ? 1 : -1;
    	int pawnPositions[] = {-1, 1};
    	
    	for (int pawnPosition : pawnPositions){
        	attackingPiece = getPieceAt(Position.getPositionOffset(kingPosition, pawnPosition, moveAmount));    	
        	if ((attackingPiece !=null) &&(attackingPiece.getOwner() != playerTurn) && (attackingPiece instanceof Pawn)){     	
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true;    	
        	}
    	}
    	
    	// Check if a Bishop/Rook/Queen has an attack path
    	int count = 0;       	
    	
        boolean[] directionValid = {true, true, true, true, true, true, true, true};
        int [][] offset = {
        		{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}
        };
        
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (directionValid[0] || directionValid[1] || directionValid[2] || directionValid[3] || 
    			directionValid[4] || directionValid[5] || directionValid[6] || directionValid[7]){
    		count++;
    		
    		for (int i = 0; i < 8; i++){
    			if (directionValid[i]){
    				    				
        			attackingPiecePosition = Position.getPositionOffset(kingPosition, count*offset[i][0], count*offset[i][1]);    			
        			if (attackingPiecePosition == null){
        				directionValid[i] = false;    			
        			} 
        			
        			attackingPiece = getPieceAt(attackingPiecePosition);
        			
        			if (attackingPiece != null) {
        				if (attackingPiece.getOwner() == playerTurn){
        					directionValid[i] = false;
        				} else if (((Math.abs(offset[i][0])>0) && (Math.abs(offset[i][1])>0)) && (attackingPiece instanceof Bishop) || (attackingPiece instanceof Queen)){
        					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        					return true;
        				} else if (((Math.abs(offset[i][0])==0) || (Math.abs(offset[i][1])==0)) && (attackingPiece instanceof Rook) || (attackingPiece instanceof Queen)){
        					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        					return true;
        				}
    	    		}    				
    				
    			}
    		}
    		
    	}

    	// Check if Knight is in attacking position
    	int [][] newPositions = {
    			{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}
    	};

    	for (int posOffset[] : newPositions){
    		attackingPiecePosition = Position.getPositionOffset(kingPosition, posOffset[0], posOffset[1]);
    		if (attackingPiecePosition != null) {
        		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece instanceof Knight)){
        			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        			return true;    		
        		}    	
    		}
    	}
    	
    	fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    	
    	return kingIsInCheck;
    }
    
    public void setKingPosition(Player owner, Position position){
    	if (owner == Player.White)
    		whiteKingPosition = position;
    	else
    		blackKingPosition = position;
    }
    
    private void fixPosition(Piece piece, Piece capturedPiece, Position finalPosition, Position initialPosition, Player playerTurn){
    	// Revert the position of the piece to it's original position
    	this.removePiece(finalPosition);
    	this.placePiece(piece, initialPosition);
    	if (capturedPiece != null){
    		this.placePiece(capturedPiece, finalPosition);
    	}
    		
    	
    	if (piece instanceof King) {
    		if (playerTurn == Player.White){    			
    			whiteKingPosition = initialPosition;
    		} else {    			
    			blackKingPosition = initialPosition;
    		}
    	} 
    }
    
    public void nextTurn(){
    	if (currentPlayer == Player.White)
    		currentPlayer = Player.Black;
    	else {
    		currentPlayer = Player.White;
    	}
    }
   
}
