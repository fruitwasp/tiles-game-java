package helper;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	public Button(String text) {
		super(text);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
