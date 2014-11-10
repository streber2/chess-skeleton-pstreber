package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner, Position initialPosition) {
        super(owner, initialPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	List<String> possibleMoves = new ArrayList<String>();
    	Position newPosition = null;

    	int count = 0;
    	// Bishop can move in a diagonal straight line (up left, up right, down left, down right). Can move until colliding with a unit.
    	    	
    	boolean loop = true;
    	boolean upright=true, downright=true, upleft=true, downleft = true;
        	
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (loop){
    		count++;
    		
    		// Moves going up right
    		if (upright){
	    		newPosition = Position.getPositionOffset(this.currentPosition, count, count);
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				upright = false;
	    			}
	    		} else {
	    			upright = false;
	    		}
    		}
    		
    		// Moves going down right
    		if (downright){
	    		newPosition = Position.getPositionOffset(this.currentPosition, count, (count*-1));
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) { 
	    				downright = false;
	    			}
	    		} else {
	    			downright = false;
	    		}
    		}
    		
    		// Moves going up left
    		if (upleft){    			    		
	    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), count);
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				upleft = false;
	    			}
	    		} else {
	    			upleft = false;
	    		}
    		}
    		
    		// Moves going down left
    		if (downleft){    			
	    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), (count*-1));
	    		if (validSpot(newPosition, gameState)) {
	    			if (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition))
	    				possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
	    			
	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) {
	    				downleft = false;
	    			}
	    		} else {
	    			downleft = false;
	    		}  		
    		}	
    		if (upright || downright || upleft || downleft)
    			continue;
    		else 
    			loop = false;
    		
    	}
    	
    	return possibleMoves;
    }
}
