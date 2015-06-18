package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import model.Game;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private static final Dimension SIZE = new Dimension(400, 700);
	private static final String PHRASE_TITLE = "Tiles";
	
	private ScorePanel scorePanel;
	private GamePanel gamePanel;

	public GameFrame(Game game) {
		setTitle(PHRASE_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(SIZE);
		setResizable(false);
		setLayout(new BorderLayout());
		getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		getRootPane().setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		
		scorePanel = new ScorePanel(game);		
		gamePanel = new GamePanel(game);		
		add(scorePanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				game.onExitGame();				
			}
		});
	}
	
}
