package model;

import java.util.ArrayList;

import observer.GameObserver;
import observer.ScoreObserver;

public class Game {

	private ScoreObserver scoreObserver;
	private GameObserver gameObserver;
	
	private int score, highscore;
	
	private Grid grid;
	private Dock dock;
	private ArrayList<Dock.PuzzleBlock> puzzleBlocks;
	
	public Game() {		
		grid = new Grid(this);	
		dock = new Dock(this);
		puzzleBlocks = new ArrayList<>();		
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
	
	public ArrayList<Dock.PuzzleBlock> getPuzzleBlocks() {
		return puzzleBlocks;
	}
	
	public void setScoreObserver(ScoreObserver scoreObserver) {
		this.scoreObserver = scoreObserver;
	}
	
	public void setGameObserver(GameObserver gameObserver) {
		this.gameObserver = gameObserver;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}	
	
	public void addScore(int score) {
		this.score += score;
	}	

}
