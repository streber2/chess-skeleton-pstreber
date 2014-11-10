package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner, Position initialPosition) {
        super(owner, initialPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	List<String> possibleMoves = new ArrayList<String>();    	
    	Position newPosition = null;
    	

    	// King can move one spot in any direction
    	
    	//Up    	  	    	
    	newPosition = Position.getPositionOffset(this.currentPosition, 0, 1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	//Up right
    	newPosition = Position.getPositionOffset(this.currentPosition, 1, 1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	//Right
    	newPosition = Position.getPositionOffset(this.currentPosition, 1, 0);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	//Down right
    	newPosition = Position.getPositionOffset(this.currentPosition, 1, -1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	   	
    	//Down
    	newPosition = Position.getPositionOffset(this.currentPosition, 0, -1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	
    	//Down left
    	newPosition = Position.getPositionOffset(this.currentPosition, -1, -1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	//Left
    	newPosition = Position.getPositionOffset(this.currentPosition, -1, 0);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());
    	
    	//Up left
    	newPosition = Position.getPositionOffset(this.currentPosition, -1, 1);
    	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, this.currentPosition, newPosition)))
    		possibleMoves.add(this.currentPosition.toString() + " " + newPosition.toString());

    	return possibleMoves;
    }
}
