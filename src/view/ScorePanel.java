package view;

import helper.Label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import observer.ScoreObserver;
import controller.GameController;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements ScoreObserver {

	private GameController gameController;
	
	private static final Dimension SIZE = new Dimension(0, 80);
	
	private static BufferedImage throphyImage;	
	private Label scoreLabel, highscoreLabel;
	
	public ScorePanel(GameController gameController) {
		this.gameController = gameController;
		
		setLayout(new FlowLayout());
		setPreferredSize(SIZE);
		setOpaque(false);
		
		try {
			throphyImage = ImageIO.read(new File("res/throphy.png"));
		}
		catch (Exception e) {}
		
		scoreLabel = (Label) add(new Label());
		highscoreLabel = (Label) add(new Label());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	//	g.drawImage(throphyImage, 0, 0, this);		
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
