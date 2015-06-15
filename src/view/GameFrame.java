package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private static final Dimension SIZE = new Dimension(400, 700);
	private static final String PHRASE_TITLE = "Tiles";
	
	private JPanel scorePanel, gamePanel;
	
	public GameFrame(Game game) {
		setTitle(PHRASE_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(SIZE);
		setResizable(false);
		setLayout(new BorderLayout());
		getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		getRootPane().setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		
		this.scorePanel = new ScorePanel(game);
		this.gamePanel = new GamePanel(game);
		
		add(scorePanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
