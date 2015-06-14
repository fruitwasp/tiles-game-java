package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener {
	
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);
	
	public GamePanel(Game game) {
		setBackground(BACKGROUND_COLOR);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		add(new ScorePanel(game), BorderLayout.NORTH);
		add(new GridPanel(game.getGrid()), BorderLayout.CENTER);
		add(new DockPanel(game.getDock()), BorderLayout.SOUTH);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
