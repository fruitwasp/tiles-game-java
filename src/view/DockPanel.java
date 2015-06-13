package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import observer.DockObserver;
import controller.GameController;

@SuppressWarnings("serial")
public class DockPanel extends JPanel implements DockObserver {
	
	private GameController gameController;
	
	private static final Dimension SIZE = new Dimension(0, 80);
	
	public DockPanel(GameController gameController) {
		this.gameController = gameController;
		
		setBackground(Color.BLACK);
		setLayout(new FlowLayout());
		setPreferredSize(SIZE);
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
