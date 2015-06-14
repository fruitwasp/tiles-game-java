package model;

import observer.GridObserver;

public class Grid {
	
	private GridObserver gridObserver;
	
	private Game game;
	
	private String[][] blocks;
	
	public Grid(Game game) {
		this.game = game;
		
		blocks = new String[10][10];
	}
	
	public void generate() {
		// TODO: load from savegame

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				blocks[y][x] = ".";
				
				System.out.print(".");
			}
			
			System.out.println("");
		}
		
		gridObserver.onGridLoaded(blocks);
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setObserver(GridObserver gridObserver) {
		this.gridObserver = gridObserver;
	}
	
	public GridObserver getObserver() {
		return gridObserver;
	}
}
