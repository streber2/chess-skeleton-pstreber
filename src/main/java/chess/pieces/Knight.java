package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;

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
    public List<String> getPossibleMoves(GameState gameState) {
    	return null;
    }
}
