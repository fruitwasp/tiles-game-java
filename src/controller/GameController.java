package controller;

import java.util.ArrayList;

import model.Tile;
import observer.DockObserver;
import observer.GridObserver;
import observer.ScoreObserver;
import view.GameFrame;
import view.GamePanel;

public class GameController {
	
	private GameFrame gameFrame;
	
	private DockObserver dockObserver;
	private GridObserver gridObserver;
	private ScoreObserver scoreObserver;	
	
	private ArrayList<Tile> tiles;
	
	public GameController() {
		
		gameFrame = new GameFrame();
		gameFrame.setPanel(new GamePanel(this));
		
		tiles = new ArrayList<>();
	}
	
	public void setDockObserver(DockObserver dockObserver) {
		this.dockObserver = dockObserver;
	}
	
	public void setGridObserver(GridObserver gridObserver) {
		this.gridObserver = gridObserver;
	}
	
	public void setScoreObserver(ScoreObserver scoreObserver) {
		this.scoreObserver = scoreObserver;
	}
	
	public DockObserver getDockObserver() {
		return dockObserver;
	}
	
	public GridObserver getGridObserver() {
		return gridObserver;
	}
	
	public ScoreObserver getScoreObserver() {
		return scoreObserver;
	}
	
}
