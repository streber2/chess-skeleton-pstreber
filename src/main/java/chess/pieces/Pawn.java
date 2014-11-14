package chess.pieces;

import chess.GameState;
import chess.Player;
import chess.Position;

import java.util.List;
import java.util.ArrayList;

/**
 * The Pawn
 */
public class Pawn extends Piece {
	
	private boolean hasMoved = false;
	
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState, Position currentPosition) {
    	
    	// Pawn moveset rules:
    	// A pawn can move one spot forward if no piece is there
    	// A pawn can move two spots forward if no pieces are in the way and if they have yet to move
    	// A pawn can move diagonal forward if it is capturing an enemy piece
    	
    	List<String> possibleMoves = new ArrayList<String>();
    	Piece enemyPiece = null;
    	
    	//TODO: Might be useless, remove after testing.
		/*if (!((Position.MIN_ROW < currentPosition.getRow()) && (currentPosition.getRow() < Position.MAX_ROW))){
			return null;
		} */
		
		int moveAmount = (this.owner == Player.White) ? 1 : -1;    	    		
	
		// Pawn can move one spot forward, if no piece is there
    	Position newPosition = Position.getPositionOffset(currentPosition,0, moveAmount);
    	enemyPiece = gameState.getPieceAt(newPosition);
    	// Can't use Piece.validSpot() because pawn cannot take a piece going straight forward
		if ((enemyPiece == null) && newPosition!=null) {
			if (!gameState.isKingCheck(owner, this, currentPosition, newPosition)){
				possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());
			}
			
			// If the pawn has not moved yet, it can move two spots forward, if there is no piece there
			if (hasMoved == false){       		
				newPosition = Position.getPositionOffset(newPosition, 0, moveAmount);
				if (gameState.getPieceAt(newPosition) == null){
					if (!gameState.isKingCheck(owner, this, currentPosition, newPosition))
						possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());    			
				}
			}
			
		}
    		    	
		// If the pawn has an enemy piece diagonal to the left or right, it can take that piece unless it's a King		
    	int pawnPositions[] = {-1, 1};
    	
    	for (int pawnPosition : pawnPositions){
    		
    		newPosition = Position.getPositionOffset(currentPosition, pawnPosition, moveAmount);
    		enemyPiece = gameState.getPieceAt(newPosition);
    		if (enemyPiece !=null) {
    			if ((enemyPiece.getOwner() != this.owner) && (!(enemyPiece instanceof King))){
    				if (!gameState.isKingCheck(owner, this, currentPosition, newPosition))
    					possibleMoves.add(currentPosition.toString() + " " + newPosition.toString());
    			}			
    		}    		
    	}
		
    	return possibleMoves;
    }
    
    /**
     * This method is not yet implemented, would be for goal 2.
     * However, for testing it needs to be here simple to set the flag hasMoved to true, otherwise the pawn could move 2 spots ever turn.
     */
    public void movePiece(){
    	hasMoved = true;
    }
}

