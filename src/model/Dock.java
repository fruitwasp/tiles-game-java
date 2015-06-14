package model;

import observer.DockObserver;

public class Dock {	

	private DockObserver dockObserver;
	
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
			String str = ALLOWED_PUZZLE_BLOCKS[(int) (Math.random() * ALLOWED_PUZZLE_BLOCKS.length)];
			
			puzzleBlocks[i] = new PuzzleBlock(str);
		}
		
		dockObserver.onDockLoaded(puzzleBlocks);
	}
	
	public void setObserver(DockObserver dockObserver) {
		this.dockObserver = dockObserver;
	}
	
	public DockObserver getObserver() {
		return dockObserver;
	}
	
	public class PuzzleBlock {
		
		private boolean[][] blocks;
		
		public PuzzleBlock(String puzzleBlockScheme) {
			blocks = new boolean[5][5];
			
			char[] blocksScheme = puzzleBlockScheme.toCharArray();
			
			int i = 0;
			for (int y = 0; y < blocks.length; y++) {
				for (int x = 0; x < blocks.length; x++) {
					blocks[y][x] = blocksScheme[i] == '.';
					
					i++;
				}
				
			}
			
		}
		
		public boolean[][] getBlocks() {
			return blocks;
		}
		
	}
	
}
