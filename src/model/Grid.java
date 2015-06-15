package model;

import java.util.ArrayList;

import model.Dock.PuzzleBlock;
import observer.GameObserver;

public class Grid {
	
	private GameObserver gridObserver;
	
	private Game game;
	
	private static final int ROW_COUNT = 10;
	private static final int COLUMN_COUNT = 10;
	private PuzzleBlock[][] blocks;
	
	public Grid(Game game) {
		this.game = game;
		
		blocks = new PuzzleBlock[ROW_COUNT][COLUMN_COUNT];
	}
	
	public void generate() {
		// TODO: load from savegame

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				blocks[y][x] = null;
				
				System.out.print(".");
			}
			
			System.out.println("");
		}
		
		game.getGameObserver().onGridLoaded(blocks);
	}
	
	public String toString() {
		String str = "";
		
		for (int row = 0; row < ROW_COUNT; row++) {
			for (int column = 0; column < COLUMN_COUNT; column++) {
				str += ".";
			}
		}
		
		return str;
	}
	
	public Game getGame() {
		return game;
	}
	
	public PuzzleBlock[][] getPuzzleBlocks() {
		return blocks;
	}
	
}
