package view;

import helper.Button;
import helper.Label;

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
	
	private static final Dimension SIZE = new Dimension(400, 80);
	
	private static final String PHRASE_SURRENDER = "Surrender";
	
	private static BufferedImage throphyImage;	
	private Label scoreLabel, highscoreLabel;
	private Button surrenderButton;
	
	public ScorePanel(Game game) {		
		setPreferredSize(SIZE);
		setLayout(new FlowLayout());
		setOpaque(false);
		
		try {
			throphyImage = ImageIO.read(new File("res/throphy.png"));
		}
		catch (Exception e) {}
		
		scoreLabel = (Label) add(new Label());
		scoreLabel.setText("0");
		highscoreLabel = (Label) add(new Label());
		highscoreLabel.setText("100");
		
		surrenderButton = (Button) add(new Button(PHRASE_SURRENDER));
		
		game.setObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void onScoreChanged(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHighscoreChanged(int highscore) {
		// TODO Auto-generated method stub
		
	}

}
