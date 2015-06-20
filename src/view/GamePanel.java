package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import model.Dock.PuzzleBlock;
import model.Game;
import observer.GameObserver;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements GameObserver, MouseListener, MouseMotionListener {
	
	private static final int PUZZLE_BLOCK_COUNT = 3, MAX_TILE_COUNT = 5;
	private static final int ROW_COUNT = 10, COLUMN_COUNT = 10;
	private static final int TILE_SIZE = 32, TILE_GAP = 2, TILE_MINI_SIZE = 20;
	private static final Color COLOR_DEFAULT = new Color(220, 220, 220);
	private static final int DOCK_Y = 400;
	
	private Game game;
	
	private GameOverPanel gameOverPanel;
	
	private PuzzleBlock[] puzzleBlocks;
	private int selectedPuzzleBlockId;
	
	private char[][] blocks;
	private int x, y;
	
	private static final Color 
		COLOR_PURPLE = new Color(122, 130, 207),
		COLOR_ORANGE = new Color(244, 199, 62),
		COLOR_DARK_ORANGE = new Color(219, 140, 69),
		COLOR_GREEN = new Color(105, 219, 126),
		COLOR_DARK_PURPLE = new Color(211, 82, 122),
		COLOR_LIGHT_GREEN = new Color(157, 235, 81),
		COLOR_DARK_RED = new Color(199, 82, 79),
		COLOR_LIGHT_BLUE = new Color(104, 193, 224),
		COLOR_EMERALD_GREEN = new Color(97, 229, 172);
	
	public GamePanel(Game game) {
		setLayout(new GridLayout(10, 10, 2, 2));
		setOpaque(false);
		
		this.game = game;
		
		puzzleBlocks = new PuzzleBlock[PUZZLE_BLOCK_COUNT];
		blocks = new char[COLUMN_COUNT][ROW_COUNT];
		selectedPuzzleBlockId = -1;
		
		gameOverPanel = new GameOverPanel(game, true);
		
		addMouseListener(this);
		addMouseMotionListener(this);		
		
		game.setGameObserver(this);		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("redraw");
		
		for (int x = 0; x < COLUMN_COUNT; x++) {
			for (int y = 0; y < ROW_COUNT; y++) {
				int x1 = x * TILE_SIZE + x * TILE_GAP;
				int y1 = y * TILE_SIZE + y * TILE_GAP;
				
				drawTile(blocks[x][y] == '.' ? COLOR_DEFAULT : getColorByChar(blocks[x][y]), x1, y1, true, g);
			}
			
		}
		
		int x = 0;
		for (int i = 0; i < puzzleBlocks.length; i++) {	
			if (puzzleBlocks[i] != null) {
				drawPuzzleBlock(puzzleBlocks[i], x, 400, g);
			}			
			
			x += 113;
		}
		
	}
	
	public void clear() {
		for (int x = 0; x < COLUMN_COUNT; x++) {
			for (int y = 0; y < ROW_COUNT; y++) {
				blocks[x][y] = '.';
			}
			
		}
		
	}
	
	public void addTile(PuzzleBlock puzzleBlock, int x, int y) {
	//	blocks[x][y] = puzzleBlock;
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
					
					boolean isSelected = selectedPuzzleBlockId > -1 ? puzzleBlock == puzzleBlocks[selectedPuzzleBlockId] : false;
					
					int x1 = (isSelected ? this.x : x) + i * (isSelected ? TILE_SIZE : TILE_MINI_SIZE) + i * TILE_GAP;
					int y1 = (isSelected ? this.y : y) + j * (isSelected ? TILE_SIZE : TILE_MINI_SIZE) + j * TILE_GAP;
					
					drawTile(getColorByChar(puzzleBlock.getLetter()), x1, y1, isSelected, g);				
				}
				
			}
			
		}
		
	}
	
	private Color getColorByChar(char letter) {
		Color color = Color.BLACK;
		
		switch(letter) {
		case 'A':
			color = COLOR_PURPLE;
			break;
		case 'B': case 'C':
			color = COLOR_ORANGE;
			break;
		case 'D': case 'E':
			color = COLOR_DARK_ORANGE;
			break;
		case 'F': case 'G': case 'H': case 'I':
			color = COLOR_GREEN;
			break;
		case 'J': case 'K':
			color = COLOR_DARK_PURPLE;
			break;
		case 'L':
			color = COLOR_LIGHT_GREEN;
			break;
		case 'M': case 'N':
			color = COLOR_DARK_RED;
			break;
		case 'O': case 'P': case 'Q': case 'R':
			color = COLOR_LIGHT_BLUE;
			break;
		case 'S':
			color = COLOR_EMERALD_GREEN;
			break;
	/*	default:
			color = Color.BLACK; */
		}
		
		return color;
	}

	@Override
	public void onGridLoaded(char[][] blocks) {
		this.blocks = blocks;
		
		repaint();
	}

	@Override
	public void onDockLoaded(PuzzleBlock[] puzzleBlocks) {
		this.puzzleBlocks = puzzleBlocks;
		
		repaint();		
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
		System.out.println("mouse pressed");
		
		int i = -1;
		int y = 400;
		int puzzleBlockWidthPlusGap = 113;
		
		if (me.getY() > y && me.getY() < y + puzzleBlockWidthPlusGap) {
			for (int a = 0; a < 3; a++) {
				if (puzzleBlocks[a] == null) {
					continue;
				}
				
				int xmin = a * puzzleBlockWidthPlusGap;
				int xmax = xmin + puzzleBlockWidthPlusGap;
				
				if (me.getX() > xmin && me.getX() < xmax) {					
					i = a;
					break;
				}
			}
		}
		
		if (i < 0 || i > 2) { return; }
		
		this.selectedPuzzleBlockId = i;
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		System.out.println("mouse released");
		
		if (selectedPuzzleBlockId < 0) {
			return;
		}
		
		if (x > 0 && y > 0 && x < 338 && y < 338) {
			game.onPuzzleBlockReleased(puzzleBlocks[selectedPuzzleBlockId], selectedPuzzleBlockId, x, y);
		}
		
		this.selectedPuzzleBlockId = -1;
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		if (selectedPuzzleBlockId == -1) {
			return;
		}
		
		x = me.getX();
		y = me.getY();
		
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(boolean surrendered) {
		gameOverPanel.setVisible(true);
		gameOverPanel.setSurrendered(surrendered);
		gameOverPanel.setHighscore(game.getHighScore());
		gameOverPanel.revalidate();
		
		add(gameOverPanel, BorderLayout.CENTER);
		
		revalidate();
	}

	@Override
	public void onCancelGameOver() {
		gameOverPanel.setVisible(false);
		remove(gameOverPanel);
		
		revalidate();
	}

	@Override
	public void onRetryGame() {
		gameOverPanel.setVisible(false);
		remove(gameOverPanel);
		
		revalidate();
		
	}
	
}
