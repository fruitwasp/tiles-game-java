package model;

import java.util.ArrayList;

import observer.ScoreObserver;

public class Game {
	
	private ScoreObserver scoreObserver;
	
	private int score, highscore;
	
	private Grid grid;
	private Dock dock;
	private ArrayList<Dock.PuzzleBlock> puzzleBlocks;
	
	public Game() {		
		grid = new Grid();	
		dock = new Dock();
		puzzleBlocks = new ArrayList<>();		
	}
	
	public ScoreObserver getObserver() {
		return scoreObserver;
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
	
	public void setObserver(ScoreObserver scoreObserver) {
		this.scoreObserver = scoreObserver;
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
