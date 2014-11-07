package chess.pieces;

import chess.GameState;
import chess.Player;

import java.util.List;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
    
    @Override
    public List<String> getPossibleMoves(GameState gameState) {
    	return null;
    }
}
