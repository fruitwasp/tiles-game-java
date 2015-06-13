package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private static final String PHRASE_TITLE = "Tiles";
	private static final Dimension SIZE = new Dimension(400, 700);
	
	private JPanel currentPanel;
	
	public GameFrame() {
		setTitle(PHRASE_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setMinimumSize(SIZE);		
		setLocationRelativeTo(null);		
		setVisible(true);
	}
	
	public void setPanel(JPanel panel) {
		if (currentPanel != null) {
			remove(currentPanel);
		}
		
		add(panel, BorderLayout.CENTER);
		
		currentPanel = panel;
		
		revalidate();
	}
}
