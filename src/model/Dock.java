package model;

import observer.GameObserver;

public class Dock {
	
	private Game game;
	private GameObserver gameObserver;
	
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
	private static final char[] ALLOWED_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's'};
	private PuzzleBlock[] puzzleBlocks;
	private int puzzleBlockCount;
	
	public Dock(Game game) {
		this.game = game;
		
		puzzleBlocks = new PuzzleBlock[3];
		puzzleBlockCount = 0;
	}
	
	public void setGameObserver(GameObserver gameObserver) {
		this.gameObserver = gameObserver;
	}
	
	public Game getGame() {
		return game;
	}
	
	public PuzzleBlock[] getPuzzleBlocks() {
		return puzzleBlocks;
	}
	
	private char fetchLetterById(int id) {
		return Dock.ALLOWED_LETTERS[id];
	}
	
	public void generatePuzzleBlocks() {
		for (int i = 0; i < 3; i++) {
			int id = (int) (Math.random() * (ALLOWED_PUZZLE_BLOCKS.length - 1));
			String str = ALLOWED_PUZZLE_BLOCKS[id];
			
			System.out.println(str);
			
			puzzleBlocks[i] = new PuzzleBlock(str, fetchLetterById(id));
		}
		
		System.out.println("nieuwe blokjes gegenereerd.");
		
		puzzleBlockCount = 3;
		
		gameObserver.onDockLoaded(puzzleBlocks);
	}
	
	public void onPuzzleBlockPlaced(int placedPuzzleBlockId) {
		puzzleBlocks[placedPuzzleBlockId] = null;
		puzzleBlockCount--;
		
		if (puzzleBlockCount < 1) {
			System.out.println("ik wil nieuwe blokjes!");
			
			generatePuzzleBlocks();
		}
		
		gameObserver.onDockLoaded(puzzleBlocks);
	}
	
	public class PuzzleBlock {
		
		private String pattern;
		private boolean[][] blocks;
		private int blocksCount;
		private char letter;
		
		public PuzzleBlock(String pattern, char letter) {
			blocks = new boolean[5][5];
			
			this.pattern = pattern;
			this.letter = letter;
			
			char[] splittedPattern = pattern.toCharArray();
			
			int i = 0;
			for (int y = 0; y < blocks.length; y++) {
				for (int x = 0; x < blocks.length; x++) {
					boolean notDot = splittedPattern[i] != '.';
					
					blocks[y][x] = notDot;
					
					if (notDot) {
						blocksCount++;
					}
					
					i++;
				}
				
			}
			
		}
		
		public char getLetter() {
			return letter;
		}
		
		public boolean[][] getBlocks() {
			return blocks;
		}
		
		public int getBlocksCount() {
			return blocksCount;
		}
		
		public String toString() {
			return pattern;
		}
	}
	
}
