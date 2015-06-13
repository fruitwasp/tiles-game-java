package helper;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Grid extends JPanel {

	private static final int LENGTH = 10;
	private static final GridLayout LAYOUT = new GridLayout(LENGTH, LENGTH);
	private Tile[][] tiles;
	
	public Grid() {
		setLayout(LAYOUT);
		
		tiles = new Tile[10][10];
	}
	
	public void setTile(Tile tile, int x, int y) {
		tiles[x][y] = tile;
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public void fill() {
		for (int y = 0; y < LENGTH; y++) {
			for (int x = 0; x < LENGTH; x++) {
				tiles[y][x] = new Tile();
			}
		}
	}
}
