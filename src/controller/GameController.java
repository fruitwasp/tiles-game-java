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
		
		gameFrame = new GameFrame(game);
		
		game.loadGame();
	}
	
}
