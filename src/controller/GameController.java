package controller;

import model.Game;
import view.GameFrame;
import view.GamePanel;

public class GameController {
	
	private Game game;
	
	private GamePanel gamePanel;
	private GameFrame gameFrame;
	
	public GameController() {
		game = new Game();
		
		gamePanel = new GamePanel(game);
		
		gameFrame = new GameFrame();
		gameFrame.setPanel(gamePanel);
		
		game.getGrid().generate();
		game.getDock().generatePuzzleBlocks();
	}
	
}
