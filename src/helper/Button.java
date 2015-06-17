package helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	public Button(String text) {
		super(text);
		
		setFocusPainted(false);
		setBackground(Color.WHITE);
		setFont(FONT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}