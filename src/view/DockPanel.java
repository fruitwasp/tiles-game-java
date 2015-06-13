package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Dock;
import model.Dock.PuzzleBlock;
import observer.DockObserver;

@SuppressWarnings("serial")
public class DockPanel extends JPanel implements DockObserver {
	
	private static final Dimension SIZE = new Dimension(400, 80);
	
	public DockPanel(Dock dock) {		
		setPreferredSize(SIZE);
		setLayout(new FlowLayout());
		setOpaque(false);
		
		dock.setObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void onDockLoaded(PuzzleBlock[] puzzleBlocks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleBlockPlaced(int index) {
		// TODO Auto-generated method stub
		
	}
	
}
