package helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {

	private static final Font FONT = new Font("Arial", Font.BOLD, 22);
	
	public Label() {		
		setFont(FONT);
		setForeground(Color.BLUE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}