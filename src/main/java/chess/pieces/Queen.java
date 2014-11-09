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
    public Queen(Player owner, Position initialPosition) {
        super(owner, initialPosition);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	List<String> possibleMoves = new ArrayList<String>();
    	
    	Bishop testBishop = new Bishop(this.owner, this.currentPosition);
    	Rook testRook = new Rook(this.owner, this.currentPosition);
    	
    	possibleMoves.addAll(testBishop.getPossibleMoves(gameState));
    	possibleMoves.addAll(testRook.getPossibleMoves(gameState));
    	
    	
    	return possibleMoves;
    }
}
