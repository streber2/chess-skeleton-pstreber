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
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState, Position currentPosition) {
    	List<String> possibleMoves = new ArrayList<String>();    	
    	Position newPosition = null;
    	
    	// King can move one spot in any direction
    	int [][] newPositions = {
    			{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
    	};

    	for (int posOffset[] : newPositions){
        	newPosition = Position.getPositionOffset(currentPosition, posOffset[0], posOffset[1]);
        	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, currentPosition, newPosition)))
        		possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());    	
    	}
    	
    	return possibleMoves;
    }
}
