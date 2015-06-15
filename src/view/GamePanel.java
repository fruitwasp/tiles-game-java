package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import observer.GameObserver;
import model.Dock.PuzzleBlock;
import model.Game;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements GameObserver, MouseListener, MouseMotionListener {
	
	private static final int PUZZLE_BLOCK_COUNT = 3;
	private static final int ROW_COUNT = 10, COLUMN_COUNT = 10;
	private static final int TILE_SIZE = 32, TILE_GAP = 2, TILE_MINI_SIZE = 20;
	private static final Color COLOR_DEFAULT = new Color(220, 220, 220);
	
	private Game game;
	
	private PuzzleBlock[] puzzleBlocks;
	private PuzzleBlock[][] blocks;
	
	private int x, y;
	
	public GamePanel(Game game) {		
		setLayout(new GridLayout(10, 10, 2, 2));
		setOpaque(false);
		
		this.game = game;
		
		puzzleBlocks = new PuzzleBlock[PUZZLE_BLOCK_COUNT];
		blocks = new PuzzleBlock[COLUMN_COUNT][ROW_COUNT];
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		game.setGameObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int x = 0; x < COLUMN_COUNT; x++) {
			for (int y = 0; y < ROW_COUNT; y++) {
				drawTile(blocks[x][y] == null ? COLOR_DEFAULT : blocks[x][y].getColor(), x * TILE_SIZE + x * TILE_GAP, y * TILE_SIZE + y * TILE_GAP, true, g);
			}
		}
		
		int x = 0;
		for (int i = 0; i < puzzleBlocks.length; i++) {
			drawPuzzleBlock(puzzleBlocks[i], x, 400, g);
			
			x += 113;
		}
	}
	
	public void clear() {
		for (int x = 0; x < COLUMN_COUNT; x++) {
			for (int y = 0; y < ROW_COUNT; y++) {
				blocks[x][y] = null;
			}
		}
	}
	
	public void addTile(PuzzleBlock puzzleBlock, int x, int y) {
		blocks[x][y] = puzzleBlock;
	}
	
	private void drawTile(Color color, int x, int y, boolean grid, Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, grid ? TILE_SIZE : TILE_MINI_SIZE, grid ? TILE_SIZE : TILE_MINI_SIZE);
	}
	
	private void drawPuzzleBlock(PuzzleBlock puzzleBlock, int x, int y, Graphics g) {
		
		boolean[][] blocks = puzzleBlock.getBlocks();
		
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (blocks[j][i]) {
					puzzleBlock.setX(x + i * TILE_MINI_SIZE + i * TILE_GAP);
					puzzleBlock.setY(y + j * TILE_MINI_SIZE + j * TILE_GAP);
					
					drawTile(puzzleBlock.getColor(), puzzleBlock.getX(), puzzleBlock.getY(), false, g);				
				}	
				
			}
			
		}
		
	}
	
	private boolean isEmpty(int x, int y) {
		return blocks[x][y] == null;
	}

	@Override
	public void onGridLoaded(PuzzleBlock[][] blocks) {
		this.blocks = blocks;
		
		revalidate();
	}

	@Override
	public void onDockLoaded(PuzzleBlock[] puzzleBlocks) {
		this.puzzleBlocks = puzzleBlocks;
		
		for (PuzzleBlock puzzleBlock : puzzleBlocks) {
			
		}
		
		revalidate();		
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		x = me.getX();
		y = me.getY();
		
		System.out.println("x: " + x + " - y: " + y);
		
		revalidate();
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}
	
}
