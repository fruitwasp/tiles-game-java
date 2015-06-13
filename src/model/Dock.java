package model;

import observer.DockObserver;

public class Dock {	

	private DockObserver dockObserver;
	
	private PuzzleBlock[] puzzleBlocks;
	
	public Dock() {
		puzzleBlocks = new PuzzleBlock[3];
	}
	
	public PuzzleBlock[] getPuzzleBlocks() {
		return puzzleBlocks;
	}
	
	public void generatePuzzleBlocks() {
		// TODO: make this
	}
	
	public class PuzzleBlock {
		
		private boolean[][] blocks;
		
		public PuzzleBlock() {
			blocks = new boolean[5][5];
		}
		
		public boolean[][] getBlocks() {
			return blocks;
		}
		
	}
	
	public void setObserver(DockObserver dockObserver) {
		this.dockObserver = dockObserver;
	}
	
	public DockObserver getObserver() {
		return dockObserver;
	}
	
}
