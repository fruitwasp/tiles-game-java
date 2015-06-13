package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
	
	public GamePanel(Game game) {
		setBackground(BACKGROUND_COLOR);
		setLayout(new BorderLayout());
		
		add(new ScorePanel(game), BorderLayout.NORTH);
		add(new GridPanel(game.getGrid()), BorderLayout.CENTER);
		add(new DockPanel(game.getDock()), BorderLayout.SOUTH);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}
