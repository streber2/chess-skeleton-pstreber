package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;

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
    public List<String> getPossibleMoves(GameState gameState) {
    	return null;
    }
}
