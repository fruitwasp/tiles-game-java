package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

import model.Dock.PuzzleBlock;
import observer.GameObserver;

public class Grid {
	
	private Game game;
	private GameObserver gameObserver;
	
	private static AudioClip clip;
	private static final int ROW_COUNT = 10, COLUMN_COUNT = 10;
	private static final int TILE_SIZE = 32, TILE_GAP = 2;
	private char[][] blocks;
	
	private int x, y, w, h;
	
	public Grid(Game game) {
		this.game = game;
		
		try {
			clip = Applet.newAudioClip(new URL("file://res/win.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		blocks = new char[ROW_COUNT][COLUMN_COUNT];
	}
	
	public void setGameObserver(GameObserver gameObserver) {
		this.gameObserver = gameObserver;
	}
	
	public void generate() {
		// TODO: load from savegame

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				blocks[y][x] = '.';
				
				System.out.print(".");
			}
			
			System.out.println("");
		}
		
		gameObserver.onGridLoaded(blocks);
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
	
	public char[][] getPuzzleBlocks() {
		return blocks;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setW(int w) {
		this.w = w;
	}
	
	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
	private boolean isOccupied(int x, int y) {
		if (x > blocks.length - 1) { return true; }
		if (y > blocks.length - 1) { return true; }
			
		return blocks[x][y] != '.';
	}
	
	public boolean onPuzzleBlockReleased(PuzzleBlock puzzleBlock, int x, int y) {
		System.out.println("puzzleblock released on grid; time for some action");
		
		boolean occupied = false;
		
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COLUMN_COUNT; j++) {
				int x1 = i * TILE_SIZE + i * TILE_GAP;
				int y1 = j * TILE_SIZE + j * TILE_GAP;
				
				int x2 = x1 + TILE_SIZE + TILE_GAP;
				int y2 = y1 + TILE_SIZE + TILE_GAP;
				
				if (x >= x1 && x < x2 && y >= y1 && y < y2) {
					System.out.println("zit tussen (" + x1 + ", " + y1 + ") & (" + x2 + ", " + y2 + ") = op plek (" + i + ", " + j + ")");
					
					boolean[][] blocks = puzzleBlock.getBlocks();
					
					System.out.print("voldoende ruimte? ");
					
					for (int a = 0; a < 5; a++) {
						for (int b = 0; b < 5; b++) {
							if (blocks[a][b]) {
								if (isOccupied(i + b, j + a)) {
									occupied = true;
									break;
								}
							}						

						}

					}
					
					if (!occupied) {
						System.out.println("ja!");
						
						for (int a = 0; a < 5; a++) {
							for (int b = 0; b < 5; b++) {
								if (blocks[a][b]) {
									this.blocks[i + b][j + a] = puzzleBlock.getLetter();
								}						

							}

						}						
						
					}

				}

			}

		}
		
		int rowsTotal = 0;

		for (int a = 0; a < ROW_COUNT; a++) {
			boolean filled = isRowFilled(a);
			
			if (filled) {
				clearRow(a);
				rowsTotal++;
				System.out.println("row filled");
			}
		}
		
		int columnsTotal = 0;
		
		for (int b = 0; b < COLUMN_COUNT; b++) {
			boolean filled = isColumnFilled(b);
			
			if (filled) {
				clearColumn(b);
				columnsTotal++;
				System.out.println("column filled");
			}
		}
		
		if (!occupied) {
			// TODO: bonus
			int lines = rowsTotal + columnsTotal;
			
			if (lines > 0) {
				clip.play();
			}
			
			int score = puzzleBlock.getBlocksCount() + 10 * lines;
			System.out.println(score);
			game.addScore(score);
		}
		
		gameObserver.onGridLoaded(blocks);
		
		return occupied;
	}
	
	private boolean isRowFilled(int y) {
		
		for (int x = 0; x < ROW_COUNT; x++) {
			if (!isOccupied(x, y)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void clearRow(int y) {
		for (int x = 0; x < COLUMN_COUNT; x++) {
			blocks[x][y] = '.';
		}
	}
	
	private boolean isColumnFilled(int x) {		
		for (int y = 0; y < COLUMN_COUNT; y++) {
			if (!isOccupied(x, y)) {
				return false;
			}
		}
		
		return true;
	}
	
	private void clearColumn(int x) {
		for (int y = 0; y < COLUMN_COUNT; y++) {
			blocks[x][y] = '.';
		}
	}
	
}
