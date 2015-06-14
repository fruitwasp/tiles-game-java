package helper;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Block extends JPanel {
	
	private String letter;
	
	public Block(String letter) {		
		this.letter = letter;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(new Color(200, 200, 200));
		g.fillRect(0, 0, width, height);
	}
	
}
