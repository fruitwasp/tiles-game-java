package helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {

	private static final Color COLOR_STEEL_BLUE = new Color(70,	130, 180);
	private static final Font FONT = new Font("Arial", Font.BOLD, 22);
	
	public Label(String text) {	
		super(text);
		
		setFont(FONT);
		setForeground(COLOR_STEEL_BLUE);
		this.setAlignmentX(HORIZONTAL);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}