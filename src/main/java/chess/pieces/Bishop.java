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
    	boolean up=true, down=true, left=true, right = true;
    	
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (loop){
    		count++;
    		
    		// Moves going up right
    		newPosition = Position.getPositionOffset(this.currentPosition, count, count);
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				up = false;
    			}
    		} else {
    			up = false;
    		}
    		
    		// Moves going down right
    		newPosition = Position.getPositionOffset(this.currentPosition, count, (count*-1));
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				down = false;
    			}
    		} else {
    			down = false;
    		}
    		
    		// Moves going up left
    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), count);
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				right = false;
    			}
    		} else {
    			right = false;
    		}
    		
    		// Moves going down left
    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), (count*-1));
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				left = false;
    			}
    		} else {
    			left = false;
    		}  		
    		
    		if (up || down || left || right)
    			continue;
    		else 
    			loop = false;
    		
    	}
    	
    	
    	
//    	// Moves going up right
//    	for (int i = this.currentPosition.getRow(), j = this.currentPosition.getColumn(); i <= Position.MAX_ROW && j <= Position.MAX_COLUMN; i++, j++){
//    		count++;
//    		newPosition = Position.getPositionOffset(this.currentPosition, count, count);
//    		
//    		if (newPosition == null){
//    			break;
//    		}
//    		else if (gameState.getPieceAt(newPosition) == null){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    		} else if (gameState.getPieceAt(newPosition).getOwner() != this.owner){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    			break;
//    		} else {
//    			break;
//    		}
//    		
//    	}
//    	
//    	count = 0;
//    	// Moves going down right
//    	for (int i = this.currentPosition.getRow(), j = this.currentPosition.getColumn(); i >= Position.MIN_ROW && j <= Position.MAX_COLUMN; i--, j++){
//    		count--;
//    		newPosition = Position.getPositionOffset(this.currentPosition, Math.abs(count), count);
//    		if (newPosition == null){
//    			break;
//    		}
//    		else if (gameState.getPieceAt(newPosition) == null){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    		} else if (gameState.getPieceAt(newPosition).getOwner() != this.owner){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    			break;
//    		} else {
//    			break;
//    		}
//    	}
//    	
//    	count=0;
//    	// Moves going up left
//    	for (int i = this.currentPosition.getRow(), j = this.currentPosition.getColumn(); i <= Position.MAX_ROW && j >= Position.MIN_COLUMN; i++, j--){
//
//    		count++;
//    		newPosition = Position.getPositionOffset(this.currentPosition, count*-1, count); 
//    		if (newPosition == null){    			
//    			break;
//    		}
//    		else if (gameState.getPieceAt(newPosition) == null){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    		} else if (gameState.getPieceAt(newPosition).getOwner() != this.owner){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    			break;
//    		} else {
//    			break;
//    		}
//    	}
//    	
//    	count=0;
//    	// Moves going down left
//    	for (int i = this.currentPosition.getRow(), j = this.currentPosition.getColumn(); i >= Position.MIN_ROW && j >= Position.MIN_ROW; i--, j--){
//
//    		count--;
//    		newPosition = Position.getPositionOffset(this.currentPosition, count, count);
//    		if (newPosition == null){
//    			break;
//    		}
//    		else if (gameState.getPieceAt(newPosition) == null){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    		} else if (gameState.getPieceAt(newPosition).getOwner() != this.owner){
//    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
//    			break;
//    		} else {
//    			break;
//    		}
//    	}
    	
    	return possibleMoves;
    }
}
