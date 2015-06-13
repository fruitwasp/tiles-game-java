package view;

import helper.Grid;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import observer.GridObserver;
import controller.GameController;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements GridObserver {

	private GameController gameController;
	
	private Grid grid;
	
	public GridPanel(GameController gameController) {
		this.gameController = gameController;
		
		setOpaque(false);
		setLayout(new BorderLayout());
		
		grid = new Grid();
		grid.fill();
		
		add(grid, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
