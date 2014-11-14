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

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState, Position currentPosition) {
    	
    	List<String> possibleMoves = new ArrayList<String>();
    	Position newPosition = null;
    	int count = 0;
    	// Rook can move left, right, up or down in a straight line. Can move until colliding with a unit.
    	
        boolean[] directionValid = {true, true, true, true};
        int [][] offset = {
        		{0,1}, {0,-1}, {1,0}, {-1,0}
        };
        
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (directionValid[0] || directionValid[1] || directionValid[2] || directionValid[3]){
    		count++;
    		
    		for (int i = 0; i < 4; i++){
    			if (directionValid[i]){
    				newPosition = Position.getPositionOffset(currentPosition, count*offset[i][0], count*offset[i][1]);
    				if (validSpot(newPosition, gameState)) {
    	    			if (!gameState.isKingCheck(owner, this, currentPosition, newPosition))
    	    				possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());
    	    			if ((gameState.getPieceAt(newPosition) !=null) && (gameState.getPieceAt(newPosition).getOwner() != this.owner)) 
    	    				directionValid[i] = false;
    	    			
    				} else {
    					directionValid[i] = false;
    				}
    			}
    		}
    		
    	}
    	
  
	
    	return possibleMoves;
    	
    }
}
