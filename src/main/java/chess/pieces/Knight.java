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
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState, Position currentPosition) {
    	List<String> possibleMoves = new ArrayList<String>();
    	Position newPosition = null;
    	
    	
    	int [][] newPositions = {
    			{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}
    	};

    	for (int posOffset[] : newPositions){
        	newPosition = Position.getPositionOffset(currentPosition, posOffset[0], posOffset[1]);
        	if ((validSpot(newPosition, gameState)) && (!gameState.isKingCheck(owner, this, currentPosition, newPosition)))
        		possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());    	
    	}   	

    	return possibleMoves;
    }

}
