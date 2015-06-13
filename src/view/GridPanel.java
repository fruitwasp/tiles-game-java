package view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Grid;
import observer.GridObserver;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements GridObserver {
	
	public GridPanel(Grid grid) {		
		setLayout(new BorderLayout());
		setOpaque(false);
		
		grid.setObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void onGridLoaded(String[][] grid) {
		// TODO Auto-generated method stub
		
	}



}
