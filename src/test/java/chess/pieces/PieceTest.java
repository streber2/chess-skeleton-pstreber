package chess.pieces;

import java.util.ArrayList;


import chess.GameState;
import chess.Position;

import chess.Player;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Unit tests for piece move lists.
 * 1. Test that each unit correctly lists it's possible moves given a starting board
 * 		a. Pieces do not list moves beyond the bounds of the board
 * 		b. Pieces only move according to the rules of chess
 * 		c. Pieces list moves that take opposing pieces (except the king)
 * 		d. 
 */
public class PieceTest {


	/**
	 * Test that each piece returns a correct list of possible moves given an initial board. 
	 */
    @Test
    public void testWhiteMovesInitialBoard() {
    
    	GameState gameState = new GameState();
        gameState.reset();      
       
        // Get moveset of pawn at a2.
        Position testPosition = new Position('a', 2);
        Piece testPiece = gameState.getPieceAt(testPosition);
        
        ArrayList<String> listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(2, listOfMoves.size());
        assertEquals("a2 a3", listOfMoves.get(0));
        assertEquals("a2 a4", listOfMoves.get(1));
        
        // Get moveset of white rook at a1 (should be null)
        testPosition = new Position('a', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white knight at b1 
        testPosition = new Position('b', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(2, listOfMoves.size());
        assertEquals("b1 c3", listOfMoves.get(0));
        assertEquals("b1 a3", listOfMoves.get(1));
        
        
        // Get moveset of white bishop at c1 (should be null)
        testPosition = new Position('c', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState,testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white queen at d1 (should be null)
        testPosition = new Position('d', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white king at e1 (should be null)
        testPosition = new Position('e', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState,testPosition));
        
        assertEquals(0, listOfMoves.size());
        
    }
    
	/**
	 * Test that each piece returns a correct list of possible moves given an initial board. 
	 */
    @Test
    public void testBlackMovesInitialBoard() {
    
    	GameState gameState = new GameState();
        gameState.reset();      
        gameState.nextTurn();
       
        // Get moveset of pawn at a7.
        Position testPosition = new Position('a', 7);
        Piece testPiece = gameState.getPieceAt(testPosition);
        
        ArrayList<String> listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(2, listOfMoves.size());
        assertEquals("a7 a6", listOfMoves.get(0));
        assertEquals("a7 a5", listOfMoves.get(1));
        
        // Get moveset of white rook at a1 (should be null)
        testPosition = new Position('a', 8);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white knight at b1 
        testPosition = new Position('b', 8);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(2, listOfMoves.size());
        assertEquals("b8 c6", listOfMoves.get(0));
        assertEquals("b8 a6", listOfMoves.get(1));
        
        
        // Get moveset of white bishop at c1 (should be null)
        testPosition = new Position('c', 8);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white queen at d1 (should be null)
        testPosition = new Position('d', 8);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState,testPosition));
        
        assertEquals(0, listOfMoves.size());
        
        // Get moveset of white king at e1 (should be null)
        testPosition = new Position('e', 8);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(0, listOfMoves.size());
        
    }
    
    /**
     * Tests the movement if some pieces are mixed up. Tests that pieces only move how they are supposed to and that they can take enemy pieces.
     */
    @Test
    public void testMovesListMixedUpBoard(){
    	
    	GameState gameState = new GameState();
        Piece testPiece;
        ArrayList<String> listOfMoves;

    	//Setup of board
    	
    	// White Pieces    	
        gameState.placePiece(new Rook(Player.White), new Position("c5"));
        gameState.placePiece(new Knight(Player.White), new Position("c3"));
        gameState.placePiece(new Bishop(Player.White), new Position("f5"));
        gameState.placePiece(new Queen(Player.White), new Position("e5"));
        gameState.placePiece(new King(Player.White), new Position("e1"));  
        gameState.setKingPosition(Player.White, new Position("e1"));
        gameState.placePiece(new Pawn(Player.White), new Position("a4"));        
        gameState.placePiece(new Pawn(Player.White), new Position("d2"));
        gameState.placePiece(new Pawn(Player.White), new Position("e2"));
        gameState.placePiece(new Pawn(Player.White), new Position("f2"));        

        // Black Pieces
        gameState.placePiece(new Rook(Player.Black), new Position("a8"));
        gameState.placePiece(new Knight(Player.Black), new Position("b8"));
        gameState.placePiece(new Bishop(Player.Black), new Position("c8"));
        gameState.placePiece(new Queen(Player.Black), new Position("d8"));
        gameState.placePiece(new King(Player.Black), new Position("e8"));
        gameState.setKingPosition(Player.Black, new Position("e8"));
        gameState.placePiece(new Bishop(Player.Black), new Position("f8"));
        gameState.placePiece(new Knight(Player.Black), new Position("g8"));
        gameState.placePiece(new Rook(Player.Black), new Position("h8"));
        gameState.placePiece(new Pawn(Player.Black), new Position("a7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("b5"));
        gameState.placePiece(new Pawn(Player.Black), new Position("c7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("d7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("e7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("f7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("g7"));
        gameState.placePiece(new Pawn(Player.Black), new Position("h7"));
      
        Pawn testPawn;
        testPawn = (Pawn)gameState.getPieceAt(new Position('a', 4));
        testPawn.movePiece();
        
      // Get moveset of pawn at a4.
        Position testPosition = new Position('a', 4);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(2, listOfMoves.size());
        assertEquals("pawn has already moved, can only move up one or attack pawn diagonal from it","a4 b5", listOfMoves.get(1));
        
        // Get moveset of rook at c5 
        testPosition = new Position('c', 5);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(5, listOfMoves.size());
        assertEquals("Spot check of move list, this move takes the pawn at c7","c5 c7", listOfMoves.get(4));
        
        // Get moveset of queen at e5
        testPosition = new Position('e', 5);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(13, listOfMoves.size());
        assertEquals("Spot check of move list, this move takes the pawn at g7", "e5 g7", listOfMoves.get(8));
        
        // Get moveset of white bishop at f5
        testPosition = new Position('f', 5);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(10, listOfMoves.size());
        assertEquals("Spot check move list, this move takes pawn at h7", "f5 h7", listOfMoves.get(4));
        
        // Get moveset of knight at c3
        testPosition = new Position('c', 3);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(6, listOfMoves.size());
        assertEquals("Spot check move list, this move takes pawn at b5", "c3 b5", listOfMoves.get(4));
        

    }
    
    @Test
    public void testKingCheck(){
    	
        GameState gameState = new GameState();
        
        gameState.placePiece(new King(Player.White), new Position("e1"));
        gameState.setKingPosition(Player.White, new Position("e1"));
        gameState.placePiece(new Pawn(Player.White), new Position("d2"));
        gameState.placePiece(new Pawn(Player.White), new Position("e2"));
        gameState.placePiece(new Pawn(Player.White), new Position("f2"));

        gameState.placePiece(new King(Player.Black), new Position("e8"));
        gameState.setKingPosition(Player.Black, new Position("e8"));
        gameState.placePiece(new Bishop(Player.Black), new Position("g3"));
        
        // Get moveset of pawn at f2.
        Position testPosition = new Position('f', 2);
        Piece testPiece = gameState.getPieceAt(testPosition);
           
        ArrayList<String> listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(1, listOfMoves.size());
        assertEquals("Pawn can only move to take the bishop, any other move results in Check","f2 g3", listOfMoves.get(0));
      
        gameState = new GameState();
        
        gameState.placePiece(new King(Player.White), new Position("e1"));
        gameState.setKingPosition(Player.White, new Position("e1"));
        
        gameState.placePiece(new King(Player.Black), new Position("e3"));
        gameState.setKingPosition(Player.Black, new Position("e3"));
        
        // Get moveset of king at e1
        testPosition = new Position('e', 1);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
                
        assertEquals(2, listOfMoves.size());
        assertEquals("King can only move from side to side, any other move puts it adjacent to enemy king","e1 f1", listOfMoves.get(0));
        assertEquals("King can only move from side to side, any other move puts it adjacent to enemy king","e1 d1", listOfMoves.get(1));
        
        gameState = new GameState();
    	
    	gameState.placePiece(new King(Player.White), new Position("e1"));
        gameState.setKingPosition(Player.White, new Position("e1"));
        gameState.placePiece(new Rook(Player.White), new Position("e2"));


        gameState.placePiece(new King(Player.Black), new Position("e8"));
        gameState.setKingPosition(Player.Black, new Position("e8"));
        gameState.placePiece(new Bishop(Player.Black), new Position("g3"));
        
        // Get moveset of pawn at f2.
        testPosition = new Position('e', 2);
        testPiece = gameState.getPieceAt(testPosition);
        
        listOfMoves = new ArrayList<String>();
        listOfMoves.addAll(testPiece.getPossibleMoves(gameState, testPosition));
        
        assertEquals(1, listOfMoves.size());
        assertEquals("The Rook can only move to block the bishop because the king is in check","e2 f2", listOfMoves.get(0));
        
    }

    

}

