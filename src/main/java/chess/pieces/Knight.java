package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner, Position initialPosition) {
        super(owner, initialPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	List<String> possibleMoves = new ArrayList<String>();
    	Position newPosition = null;    	
    	
    	//Up 2, right 1    	  	    	
    	newPosition = Position.getPositionOffset(this.currentPosition, 2, 1);    
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	
    	//Up 2, left 1
    	newPosition = Position.getPositionOffset(this.currentPosition, 2, -1);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
  
    	
    	//Up 1, right 2
    	newPosition = Position.getPositionOffset(this.currentPosition, 1, 2);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	
    	//Up 1, left 2
    	newPosition = Position.getPositionOffset(this.currentPosition, 1, -2);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	   	
    	//Down 2, right 1
    	newPosition = Position.getPositionOffset(this.currentPosition, -2, 1);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	//Down 2, left 1
    	newPosition = Position.getPositionOffset(this.currentPosition, -2, -1);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	
    	//Down 1, right 2
    	newPosition = Position.getPositionOffset(this.currentPosition, -1, 2);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	
    	//Down 1, left 2
    	newPosition = Position.getPositionOffset(this.currentPosition, -1, -2);
    	if (validSpot(newPosition, gameState))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	return possibleMoves;
    }

}
