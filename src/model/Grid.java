package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.Dock.PuzzleBlock;
import observer.GameObserver;

public class Grid {
	
	private Game game;
	private GameObserver gameObserver;
	
	private static Clip clip;
	private static final int ROW_COUNT = 10, COLUMN_COUNT = 10;
	private static final int TILE_SIZE = 32, TILE_GAP = 2;
	private static final int MAX_PUZZLE_BLOCK_LEN = 5;
	private PuzzleBlock[][] puzzleBlocks;
	private char[][] blocks;
	
	public Grid(Game game) {
		this.game = game;
		
		puzzleBlocks = new PuzzleBlock[ROW_COUNT][COLUMN_COUNT];
		blocks = new char[ROW_COUNT][COLUMN_COUNT];
	}
	
	public void setGameObserver(GameObserver gameObserver) {
		this.gameObserver = gameObserver;
	}
	
	public void generate() {		
		for (int y = 0; y < 10; y++) {			
			for (int x = 0; x < 10; x++) {
				blocks[y][x] = '.';
				
				System.out.print(blocks[y][x]);
			}
			
			System.out.println("");
		}
		
		gameObserver.onGridLoaded(blocks);
	}
	
	public void load(String[] gridLines) {
		// TODO: validate grid data
		
		for (int y = 0; y < ROW_COUNT; y++) {
			String filteredStr = gridLines[y].replaceAll("^\\s+|\\s+$", "");
			char[] lineBlocks = filteredStr.toCharArray();
			
			for (int x = 0; x < COLUMN_COUNT; x++) {
				blocks[x][y] = lineBlocks[x];
				
				System.out.print(lineBlocks[x]);
			}
			
			System.out.println("");
		}
		
		gameObserver.onGridLoaded(blocks);
	}
	
	@Override
	public String toString() {
		String str = "";
		
		for (int row = 0; row < ROW_COUNT; row++) {			
			for (int column = 0; column < COLUMN_COUNT; column++) {
				str += blocks[column][row];
			}
			
			str += "\n";			
		}
		
		System.out.println(str);
		
		return str;
	}
	
	public Game getGame() {
		return game;
	}
	
	public char[][] getPuzzleBlocks() {
		return blocks;
	}
	
	public PuzzleBlock[][] getPuzzleBlockz() {
		return puzzleBlocks;
	}
	
	private boolean isOccupied(int x, int y) {
		if (x > blocks.length - 1) { return true; }
		if (y > blocks.length - 1) { return true; }
			
		return blocks[x][y] != '.';
	}
	
	public boolean onPuzzleBlockReleased(PuzzleBlock puzzleBlock, int x, int y) {
		System.out.println("puzzleblock released on grid; time for some action");
		
		boolean occupied = false;
		
		// Loop through the grid
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COLUMN_COUNT; j++) {
				int x1 = i * TILE_SIZE + i * TILE_GAP;
				int y1 = j * TILE_SIZE + j * TILE_GAP;
				
				int x2 = x1 + TILE_SIZE + TILE_GAP;
				int y2 = y1 + TILE_SIZE + TILE_GAP;
				
				// released on the grid? (within coords)
				if (x >= x1 && x < x2 && y >= y1 && y < y2) {
					System.out.println("zit tussen (" + x1 + ", " + y1 + ") & (" + x2 + ", " + y2 + ") = op plek (" + i + ", " + j + ")");
					
					boolean[][] blocks = puzzleBlock.getBlocks();
					
					System.out.print("voldoende ruimte? ");
					
					// space available?
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
					
					// add each block of the puzzleblock
					if (!occupied) {
						System.out.println("ja!");
						
						// look 
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
				playSound();
				System.out.println("play souund");
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
	
	private void clearRow(int y) {
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
	
	private boolean checkIfOccupied(PuzzleBlock puzzleBlock, int x, int y) {
		boolean[][] blocksPuzzle = puzzleBlock.getBlocks();
		
		for (int a = 0; a < MAX_PUZZLE_BLOCK_LEN; a++) {
			for (int b = 0; b < MAX_PUZZLE_BLOCK_LEN; b++) {
				if (blocksPuzzle[a][b]) {
					// Stop as soon as it finds out the puzzleblock can't be placed on the specified x and y.
					if (isOccupied(x + b, y + a)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	// Check whether at least one of the blocks in the dock can be placed in the grid
	public boolean checkIfEnoughPlaceToContinue(PuzzleBlock[] puzzleBlocks) {
		
		int checked = 0;
		int count = 0;
		
		for (int i = 0; i < puzzleBlocks.length; i++) {
			if (puzzleBlocks[i] == null) {
				continue;
			}
			
			checked++;	
			boolean foundSpot = false;
			
			for (int j = 0; j < ROW_COUNT; j++) {
				for (int k = 0; k < COLUMN_COUNT; k++) {
					if (!checkIfOccupied(puzzleBlocks[i], j, k)) {
						foundSpot = true;
						break;
					}
				}
				
				// If there's a space available, why bother to continue?
				if (foundSpot) {
					break;
				}
				
			}
			
			if (!foundSpot) {
				count++;
				System.out.println("occupied: " + foundSpot);
				System.out.println("count: " + count);
			}
		}
		
		return count < checked;
	}
	
	private void playSound() {
		File audioFile = new File("res/bloop_x.wav");		 
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);			
			AudioFormat format = audioStream.getFormat();			 
			DataLine.Info info = new DataLine.Info(Clip.class, format);
	
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
