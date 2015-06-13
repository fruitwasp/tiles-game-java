package helper;

import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Label extends JLabel {

	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	public Label() {		
		setFont(FONT);	
	}
	
}
