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
    		newPosition = Position.getPositionOffset(this.currentPosition, 0, count);
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				up = false;
    			}
    		} else {
    			up = false;
    		}
    		
    		// Moves going down
    		newPosition = Position.getPositionOffset(this.currentPosition, 0, (count*-1));
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				down = false;
    			}
    		} else {
    			down = false;
    		}
    		
    		// Moves going right
    		newPosition = Position.getPositionOffset(this.currentPosition, count, 0);
    		if (validSpot(newPosition, gameState)) {
    			possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    			
    			if (gameState.getPieceAt(newPosition).getOwner() != this.owner) { 
    				right = false;
    			}
    		} else {
    			right = false;
    		}
    		
    		// Moves going left
    		newPosition = Position.getPositionOffset(this.currentPosition, (count*-1), 0);
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
//    	// Moves going up
//    	for (int i = this.currentPosition.getRow(); i <= Position.MAX_ROW; i++){
//    		count++;
//    		newPosition = Position.getPositionOffset(this.currentPosition, 0, count);
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
//    	// Moves going down
//    	for (int i = this.currentPosition.getRow(); i >= Position.MIN_ROW; i--){
//    		count--;
//    		newPosition = Position.getPositionOffset(this.currentPosition, 0, count);
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
//    	// Moves going to the right
//    	for (char j = this.currentPosition.getColumn(); j <= Position.MAX_COLUMN; j++){
//
//    		count++;
//    		newPosition = Position.getPositionOffset(this.currentPosition, count, 0);
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
//    	// Moves going to the left
//    	for (char j = this.currentPosition.getColumn(); j >= Position.MIN_COLUMN; j--){
//
//    		count--;
//    		newPosition = Position.getPositionOffset(this.currentPosition, count, 0);
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
