package model;

import java.awt.Color;

public class Dock {
	
	private Game game;
	
	private static final String[] ALLOWED_PUZZLE_BLOCKS = {
		"x........................",
		"xx.......................",
		"x....x...................",
		"xxx......................",
		"x....x....x..............",
		"xx....x..................",
		".x...xx..................",
		"x....xx..................",
		"xx...x...................",
		"xxxx.....................",
		"x....x....x....x.........",
		"xx...xx..................",
		"xxxxx....................",
		"x....x....x....x....x....",
		"xxx....x....x............",
		"..x....x..xxx............",
		"x....x....xxx............",
		"xxx..x....x..............",
		"xxx..xxx..xxx............"
	};
	private PuzzleBlock[] puzzleBlocks;
	
	public Dock(Game game) {
		this.game = game;
		
		puzzleBlocks = new PuzzleBlock[3];
	}
	
	public Game getGame() {
		return game;
	}
	
	public PuzzleBlock[] getPuzzleBlocks() {
		return puzzleBlocks;
	}
	
	public void generatePuzzleBlocks() {
		for (int i = 0; i < 3; i++) {
			String str = ALLOWED_PUZZLE_BLOCKS[(int) (Math.random() * (ALLOWED_PUZZLE_BLOCKS.length - 1))];
			
			System.out.println(str);
			
			puzzleBlocks[i] = new PuzzleBlock(str);
		}
		
		game.getGameObserver().onDockLoaded(puzzleBlocks);
	}
	
	public class PuzzleBlock {
		
		private boolean[][] blocks;
		private Color color;
		private int x, y;
		
		public PuzzleBlock(String blueprint) {
			blocks = new boolean[5][5];
			
			char[] splittedBlueprint = blueprint.toCharArray();
			
			int i = 0;
			for (int y = 0; y < blocks.length; y++) {
				for (int x = 0; x < blocks.length; x++) {
					blocks[y][x] = splittedBlueprint[i] != '.';
					
					i++;
				}
				
			}
			
		}
		
		public boolean[][] getBlocks() {
			return blocks;
		}
		
		public Color getColor() {
			return color;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}

	}
	
}
