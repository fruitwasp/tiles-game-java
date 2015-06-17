package view;

import helper.Button;
import helper.Label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Game;
import observer.ScoreObserver;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements ScoreObserver {
	
	private Game game;
	
	private static final Dimension SIZE = new Dimension(400, 80);
	
	private static final String PHRASE_SURRENDER = "Surrender";
	
	private static BufferedImage throphyImage;	
	private Label scoreLabel, highscoreLabel;
	private Button surrenderButton;
	
	public ScorePanel(Game game) {		
		setPreferredSize(SIZE);
		setOpaque(false);
		
		try {
	//		throphyImage = ImageIO.read(new File("res/throphy.png"));
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		
		scoreLabel = (Label) add(new Label());
		scoreLabel.setText("0");
		highscoreLabel = (Label) add(new Label());
		highscoreLabel.setText("100");
		
		surrenderButton = (Button) add(new Button(PHRASE_SURRENDER));
		
		this.game = game;
		
		game.setScoreObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void onScoreChanged(int score) {
		scoreLabel.setText("" + score);
	}

	@Override
	public void onHighscoreChanged(int highscore) {
		highscoreLabel.setText("" + highscore);		
	}

}
