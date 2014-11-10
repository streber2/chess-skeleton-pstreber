package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner, Position initialPosition) {
        super(owner, initialPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	List<String> possibleMoves = new ArrayList<String>();
    	Position newPosition = null;
    	int count = 0;
    	// Rook can move left, right, up or down in a straight line. Can move until colliding with a unit.
    	boolean loop = true;
    	boolean up=true, down=true, left=true, right = true;
    	
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (loop){
    		count++;
    		
    		// Moves going up
    		if (up){
    			newPosition = Position.getPositionOffset(this.currentPosition, 0, count);
    			if (validSpot(newPosition, gameState)) {
    				if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
    					possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    				if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) { 
    					up = false;
    				}
    			} else {
    				up = false;
    			}
    		}
    		
    		// Moves going down
    		if (down){
	    		newPosition = Position.getPositionOffset(this.currentPosition, 0, (count*-1));
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				down = false;
	    			}
	    		} else {
	    			down = false;
	    		}
    		}
    		
    		// Moves going right
    		if (right){    			    		
	    		newPosition = Position.getPositionOffset(this.currentPosition, count, 0);
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				right = false;
	    			}
	    		} else {
	    			right = false;
	    		}
    		}
    		
    		// Moves going left
    		if (left) {    				    		
	    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), 0);
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				left = false;
	    			}
	    		} else {    			
	    			left = false;
	    		}
    		}
    		
    		if (up || down || left || right)
    			continue;
    		else 
    			loop = false;
    		
    	}
	
    	return possibleMoves;
    	
    }
}
