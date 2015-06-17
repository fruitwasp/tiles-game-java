package model;

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
	
	public ScoreObserver getScoreObserver() {
		return scoreObserver;
	}
	
	public GameObserver getGameObserver() {
		return gameObserver;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getHighscore() {
		return highscore;
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
		
		if (score > highscore) {
			setHighscore(score);
		}
		
		scoreObserver.onScoreChanged(score);
	}
	
	public void setHighscore(int highscore) {
		this.highscore = highscore;
		
		scoreObserver.onHighscoreChanged(highscore);
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
		
	}

}
