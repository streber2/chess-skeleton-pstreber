package chess.pieces;

import chess.Player;

import java.util.List;

import chess.GameState;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    protected final Player owner;
    protected Position currentPosition;

    protected Piece(Player owner, Position initialPosition) {
        this.owner = owner;
        this.currentPosition = initialPosition;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }
    
    protected void setPosition(Position newPosition) {
    	currentPosition = newPosition;
    }
    
    protected Position getPosition() {
    	return currentPosition;
    }
    
    /**
     * Method to check if the spot on the board is a valid move for the Piece
     * @param newPosition
     * @param gameState
     * @return
     */
    public boolean validSpot(Position newPosition, GameState gameState){
    	Piece enemyPiece = null;
    	
    	if (newPosition!=null){
    		enemyPiece = gameState.getPieceAt(newPosition);
    		if (gameState.getPieceAt(newPosition) == null){
    			return true;        		
    		} else if ((enemyPiece.getOwner() != this.owner) && (enemyPiece.getClass()!=King.class)){
    			return true;        		
        	} else {
        		return false;
        	}
    	} else {
    		return false;
    	}    	
    }

    protected abstract char getIdentifyingCharacter();
    
    public abstract List<String> getPossibleMoves(GameState gameState);
}

