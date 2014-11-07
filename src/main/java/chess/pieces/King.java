package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;

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
    public List<String> getPossibleMoves(GameState gameState) {
    	return null;
    }
}
