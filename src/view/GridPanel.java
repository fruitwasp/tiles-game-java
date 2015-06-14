package view;

import helper.Block;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Grid;
import observer.GridObserver;

@SuppressWarnings("serial")
public class GridPanel extends JPanel implements GridObserver {
	
	private Grid grid;
	
	private Block[][] blocks;
	
	public GridPanel(Grid grid) {
		GridLayout gridLayout = new GridLayout(10, 10);
		gridLayout.setHgap(2);
		gridLayout.setVgap(2);
		
		setLayout(gridLayout);
		setOpaque(false);
		
		this.grid = grid;
		
		blocks = new Block[10][10];
		
		grid.setObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void onGridLoaded(String[][] grid) {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid.length; x++) {
				Block block = new Block(grid[y][x]);
				
				blocks[y][x] = block;
				add(block);
				
			}
		}
		
		revalidate();
	}
}
