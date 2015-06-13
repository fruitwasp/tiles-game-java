package helper;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tile extends JPanel {
	
	private static final int LEN = 10;
	private static final Color COLOR_DEFAULT = new Color(80, 80, 80);
	private Color color;
	
	private Color getColor() {
		return color == null ? color : COLOR_DEFAULT;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(getColor());
		g.fillRoundRect(0,  0, LEN, LEN,  2, 2);
	}
}