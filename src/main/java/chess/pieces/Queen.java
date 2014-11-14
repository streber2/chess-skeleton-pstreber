package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState, Position currentPosition) {
    	List<String> possibleMoves = new ArrayList<String>();
    	    	
    	Position newPosition = null;
    	
    	

    	int count = 0;
    	// Bishop can move in a diagonal straight line (up left, up right, down left, down right). Can move until colliding with a unit.
   	
        boolean[] directionValid = {true, true, true, true, true, true, true, true};
        int [][] offset = {
        		{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}
        };
        
    	//Loop outwards from the piece, checking all 4 directions for possible moves
    	while (directionValid[0] || directionValid[1] || directionValid[2] || directionValid[3] || 
    			directionValid[4] || directionValid[5] || directionValid[6] || directionValid[7]){
    		count++;
    		
    		for (int i = 0; i < 8; i++){
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
