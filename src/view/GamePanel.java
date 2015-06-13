package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.GameController;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private static final Color BACKGROUND_COLOR = new Color(60, 60, 60);
	
	public GamePanel(GameController gameController) {
		setBackground(BACKGROUND_COLOR);
		setLayout(new BorderLayout());
		
		add(new ScorePanel(gameController), BorderLayout.NORTH);		
		add(new GridPanel(gameController), BorderLayout.CENTER);		
		add(new DockPanel(gameController), BorderLayout.SOUTH);		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
