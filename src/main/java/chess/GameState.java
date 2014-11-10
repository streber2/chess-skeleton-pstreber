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
        placePiece(new Rook(Player.White, new Position("a1")), new Position("a1"));
        placePiece(new Knight(Player.White, new Position("b1")), new Position("b1"));
        placePiece(new Bishop(Player.White, new Position("c1")), new Position("c1"));
        placePiece(new Queen(Player.White, new Position("d1")), new Position("d1"));
        placePiece(new King(Player.White, new Position("e1")), new Position("e1"));
        whiteKingPosition = new Position("e1");    	
        placePiece(new Bishop(Player.White, new Position("f1")), new Position("f1"));
        placePiece(new Knight(Player.White, new Position("g1")), new Position("g1"));
        placePiece(new Rook(Player.White, new Position("h1")), new Position("h1"));
        placePiece(new Pawn(Player.White, new Position("a2")), new Position("a2"));
        placePiece(new Pawn(Player.White, new Position("b2")), new Position("b2"));
        placePiece(new Pawn(Player.White, new Position("c2")), new Position("c2"));
        placePiece(new Pawn(Player.White, new Position("d2")), new Position("d2"));
        placePiece(new Pawn(Player.White, new Position("e2")), new Position("e2"));
        placePiece(new Pawn(Player.White, new Position("f2")), new Position("f2"));
        placePiece(new Pawn(Player.White, new Position("g2")), new Position("g2"));
        placePiece(new Pawn(Player.White, new Position("h2")), new Position("h2")); 
        
     /*   placePiece(new Rook(Player.White, new Position("c5")), new Position("c5"));
        placePiece(new Knight(Player.White, new Position("c3")), new Position("c3"));
        placePiece(new Bishop(Player.White, new Position("f5")), new Position("f5"));
        placePiece(new Queen(Player.White, new Position("e5")), new Position("e5"));
        placePiece(new King(Player.White, new Position("e1")), new Position("e1"));
        whiteKingPosition = new Position("e1");
        placePiece(new Pawn(Player.White, new Position("a4")), new Position("a4"));
        Pawn testPawn;
        testPawn = (Pawn)getPieceAt(new Position('a', 4));
        testPawn.movePiece();
        placePiece(new Pawn(Player.White, new Position("d2")), new Position("d2"));
        placePiece(new Pawn(Player.White, new Position("e2")), new Position("e2"));
        placePiece(new Pawn(Player.White, new Position("f2")), new Position("f2")); */

        // Black Pieces
        placePiece(new Rook(Player.Black, new Position("a8")), new Position("a8"));
        placePiece(new Knight(Player.Black, new Position("b8")), new Position("b8"));
        placePiece(new Bishop(Player.Black, new Position("c8")), new Position("c8"));
        placePiece(new Queen(Player.Black, new Position("d8")), new Position("d8"));
        placePiece(new King(Player.Black, new Position("e8")), new Position("e8"));
        blackKingPosition = new Position("e8");
        placePiece(new Bishop(Player.Black, new Position("f8")), new Position("f8"));
        placePiece(new Knight(Player.Black, new Position("g8")), new Position("g8"));
        placePiece(new Rook(Player.Black, new Position("h8")), new Position("h8"));
        placePiece(new Pawn(Player.Black, new Position("a7")), new Position("a7"));
        placePiece(new Pawn(Player.Black, new Position("b7")), new Position("b7"));
        placePiece(new Pawn(Player.Black, new Position("c7")), new Position("c7"));
        placePiece(new Pawn(Player.Black, new Position("d7")), new Position("d7"));
        placePiece(new Pawn(Player.Black, new Position("e7")), new Position("e7"));
        placePiece(new Pawn(Player.Black, new Position("f7")), new Position("f7"));
        placePiece(new Pawn(Player.Black, new Position("g7")), new Position("g7"));
        placePiece(new Pawn(Player.Black, new Position("h7")), new Position("h7"));


        
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
    	if (piece.getClass() == King.class) {
   		
    		attackingPiecePosition = Position.getPositionOffset(finalPosition, 0, 1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class)){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}     	
        	
        	//Up right
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, 1, 1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	
        	//Right
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, 1, 0);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	
        	//Down right
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, 1, -1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	   	
        	//Down
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, 0, -1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	
        	//Down left
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, -1, -1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	
        	//Left
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, -1, 0);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ){
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
        	}
        	
        	//Up left
        	attackingPiecePosition = Position.getPositionOffset(finalPosition, -1, 1);
    		if (attackingPiecePosition !=null)
    			attackingPiece = getPieceAt(attackingPiecePosition);
        	if ((attackingPiece != null) && (attackingPiece.getClass() == King.class) ) {
        		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
        		return true; 
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
    	
    	attackingPiece = getPieceAt(Position.getPositionOffset(kingPosition, -1, moveAmount));
    	
    	if ((attackingPiece !=null) &&(attackingPiece.getOwner() != playerTurn) && (attackingPiece.getClass() == Pawn.class)){     	
    		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    		return true;    	
    	}
    		    		
    	attackingPiece = getPieceAt(Position.getPositionOffset(kingPosition, 1, moveAmount));
    	if ((attackingPiece !=null) &&(attackingPiece.getOwner() != playerTurn) && (attackingPiece.getClass() == Pawn.class)){
    		fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    		return true;
    	}
    		
    	// Check if a Bishop/Rook/Queen has an attack path

    	int count = 0;       	
    	boolean loop = true;
    	boolean upright=true, downright=true, upleft=true, downleft = true;
    	boolean up=true, right=true, down=true, left = true;
    	
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (loop){
    		count++;
    		
    		// Check upwards path
    		if (up){
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, 0, count);    			
    			if (attackingPiecePosition == null){
    				up = false;    			
    			} 
    			
    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					up = false;
    				} else if ((attackingPiece.getClass() == Rook.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}	
    		}
    		
    		// Check Up right path
    		if (upright){
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, count, count);    			
    			if (attackingPiecePosition == null){
    				upright = false;    			
    			} 

    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				
    				if (attackingPiece.getOwner() == playerTurn){
    				
    					upright = false;
    				} else if ((attackingPiece.getClass() == Bishop.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);    				
    					return true;
    				}
	    		} 
    		}
    		
    		// Check right path
    		if (right) {
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, count, 0);    			
    			if (attackingPiecePosition == null){
    				right = false;    			
    			} 
    			
    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					right = false;
    				} else if ((attackingPiece.getClass() == Rook.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}		
    		}
    		
    		// Check down right path
    		if (downright){
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, count, (count*-1));
    			
    			if (attackingPiecePosition == null){
    				downright = false;    			
    			} 
    			
    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					downright = false;
    				} else if ((attackingPiece.getClass() == Bishop.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}    			
    		}
    		
    		// Check downwards path
    		if (down){
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, 0, (count*-1));    			
    			if (attackingPiecePosition == null){
    				down = false;    			
    			} 
    			
    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					down = false;
    				} else if ((attackingPiece.getClass() == Rook.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}	    		
    		}
    		
    		// Check down left path
    		if (downleft){    			
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, (count*-1), (count*-1));
    			if (attackingPiecePosition == null){
    				downleft = false;    			
    			} 
    			
    			attackingPiece = getPieceAt(attackingPiecePosition);
    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					downleft = false;
    				} else if ((attackingPiece.getClass() == Bishop.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}  		
    		}
    		
      		// Check Left path
    		if (left){
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, 0, (count*-1));    			
    			if (attackingPiecePosition == null){
    				left = false;    			
    			}     			
    			attackingPiece = getPieceAt(attackingPiecePosition);    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					left = false;
    				} else if ((attackingPiece.getClass() == Rook.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		}	    			
    		}
    		
    		// Check up left path
    		if (upleft){    			    		
    			attackingPiecePosition = Position.getPositionOffset(kingPosition, (count*-1), count);
    			
    			if (attackingPiecePosition == null){
    				upleft = false;    			
    			}     			
    			attackingPiece = getPieceAt(attackingPiecePosition);    			
    			if (attackingPiece != null) {
    				if (attackingPiece.getOwner() == playerTurn){
    					upleft = false;
    				} else if ((attackingPiece.getClass() == Bishop.class) || (attackingPiece.getClass() == Queen.class)){
    					fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    					return true;
    				}
	    		} 
    		}    		
    		
    		if (upright || downright || upleft || downleft || up || left || right || down)
    			continue;
    		else 
    			loop = false;    		
    	}
    	
    	
    	// Check if Knight is in attacking position
    	
    	//Up 2, right 1    	  	    	
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, 2, 1);    
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)){
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}
    

    	
    	//Up 2, left 1
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, 2, -1);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)) {
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}
  
    	
    	//Up 1, right 2
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, 1, 2);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)) {
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}

    	
    	//Up 1, left 2
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, 1, -2);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)) {
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}

    	   	
    	//Down 2, right 1
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, -2, 1);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)){
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}

    	//Down 2, left 1
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, -2, -1);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)){
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}

    	
    	//Down 1, right 2
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, -1, 2);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)){
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);
    			return true;    		
    		}
    	}

    	
    	//Down 1, left 2
    	attackingPiecePosition = Position.getPositionOffset(kingPosition, -1, -2);
    	if (attackingPiecePosition != null) {
    		attackingPiece = getPieceAt(attackingPiecePosition);
    		if ((attackingPiece!=null) && (attackingPiece.getOwner()!= playerTurn) && (attackingPiece.getClass() == Knight.class)){
    			fixPosition(piece, capturedPiece, finalPosition, initialPosition, playerTurn);    		
    			return true;    		
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
    		
    	
    	if (piece.getClass() == King.class) {
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
