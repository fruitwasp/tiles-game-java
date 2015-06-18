package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import model.Dock.PuzzleBlock;
import observer.GameObserver;
import observer.ScoreObserver;

public class Game {

	private ScoreObserver scoreObserver;
	private GameObserver gameObserver;
	
	private int score, highscore;
	
	private Grid grid;
	private Dock dock;
	
	public Game() {		
		grid = new Grid(this);	
		dock = new Dock(this);
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public Dock getDock() {
		return dock;
	}
	
	public void setScoreObserver(ScoreObserver scoreObserver) {
		this.scoreObserver = scoreObserver;
	}
	
	public void setGameObserver(GameObserver gameObserver) {
		this.gameObserver = gameObserver;
		
		dock.setGameObserver(gameObserver);
		grid.setGameObserver(gameObserver);
	}
	
	public void setScore(int score) {
		this.score = score;
		
		scoreObserver.onScoreChanged(score);
	}
	
	public void setHighscore(int highscore) {
		this.highscore = highscore;
		
		scoreObserver.onHighscoreChanged(highscore);
	}	
	
	public int getHighScore() {
		return highscore;
	}
	
	public void addScore(int score) {
		int newscore = this.score + score;
		
		setScore(newscore);
	}
	
	public void onPuzzleBlockReleased(PuzzleBlock selectedPuzzleBlock, int selectedPuzzleBlockId, int x, int y) {
		boolean occupied = grid.onPuzzleBlockReleased(selectedPuzzleBlock, x, y);
		
		if (!occupied) {
			dock.onPuzzleBlockPlaced(selectedPuzzleBlockId);
		}
		
		boolean enoughPlace = grid.checkIfEnoughPlaceToContinue(dock.getPuzzleBlocks());
		if (!enoughPlace) {
			System.out.println("geen ruimte meer; je hebt verloren; more luck next time!");
			
			gameObserver.onGameOver(false);
		}
	}
	
	private int toInteger(String str) {
		int integer = -1;
		
		try {
			integer = Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return -1;
		}
		
		return integer;
	}
	
	public boolean loadGame() {
		String[] lines = new String[13];
		
		boolean badGrid = false;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/savegame.txt"));
			
			// Add every single line to the array
			int i = 0;
			String line;
			while((line = br.readLine()) != null) {
				line = line.replace("\n", "").trim();
				if (!line.matches("[0-9A-S. ]+") || line.equals(" ")) {
					badGrid = true;
					System.out.println("bad line: " + line);
					break;
				}
				
				lines[i] = line;
				
				i++;
				
				if (i > lines.length - 1) {
					break;
				}
			}
			
			br.close();
		} catch (IOException e) {
			badGrid = true;
		}
		
		// Do things with the lines
		String[] gridLines = Arrays.copyOfRange(lines, 0, 10);
		
		boolean noBlocks = false;
		int[] blocks = new int[3];
		
		boolean noScores = false;
		int score = -1;
		int highscore = -1;
		
		if (!badGrid) {
			System.out.println(lines[10]);
			String[] blcks = lines[10].split(" ");
	
			for (int i = 0; i < blcks.length; i++) {
				int a = -1;
				System.out.println(blcks[i]);
				
				try {
					a = Integer.parseInt(blcks[i]);
				}
				catch (Exception e) {
					noBlocks = true;
				}
				
				if (a > 0) {
					blocks[i] = a;
				}
			}
			
			if (!noBlocks) {				
				score = toInteger(lines[11].trim());
				highscore = toInteger(lines[12].trim());
				
				if (score < 0 || highscore < 0) {
					noScores = true;
				}
			}
		}
		
		if (noBlocks || badGrid || noScores) {
			System.out.println("De spelvoortgang kon niet worden ingeladen. Er wordt een nieuw spel aangemaakt...");
			dock.generatePuzzleBlocks();
			grid.generate();
		}
		else {
			dock.loadPuzzleBlocks(blocks);
			grid.load(gridLines);
			
			setScore(score);
			setHighscore(highscore);
		}
        
		return true;
	}
	
	public void onExitGame() {
		String str = grid.toString();
		str += dock.toString();
		str += score + "\n" + highscore;

		try {
			FileWriter fw = new FileWriter("data/savegame.txt"); 
			fw.write(str);
			fw.close();
		} catch (IOException ioe) {
			System.out.println("Er is iets fout gegaan bij het schrijven naar de schijf.");
			ioe.printStackTrace();
		}
	}
	
	public void onSurrenderButtonClicked() {
		setHighscore(score);
		gameObserver.onGameOver(true);		
	}
	
	public void onCheatButtonClicked() {
		setScore(0);
		
		dock.generatePuzzleBlocks();
	}
	
	public void onCancelGameOverButtonClicked() {
		gameObserver.onCancelGameOver();
		scoreObserver.onCloseGameOver();
	}
	
	public void onRetryButtonClicked() {
		dock.generatePuzzleBlocks();
		grid.generate();
		setScore(0);
		
		gameObserver.onRetryGame();		
		scoreObserver.onCloseGameOver();
	}
}
