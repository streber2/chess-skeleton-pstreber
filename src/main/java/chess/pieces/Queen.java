package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;

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
    public List<String> getPossibleMoves(GameState gameState) {
    	return null;
    }
}
