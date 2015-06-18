package view;

import helper.Button;
import helper.Label;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;

import model.Game;
import observer.ScoreObserver;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements ScoreObserver {
	
	private static final Dimension SIZE = new Dimension(400, 110);
	private static final int IMAGE_SIZE = 50, IMAGE_X = 137, IMAGE_Y = 10;
	private static final Component SPACING = Box.createRigidArea(new Dimension(50, 60));
	private static final String PHRASE_SURRENDER = "Surrender", PHRASE_CHEAT = "Cheat!";
	
	private static BufferedImage throphyImage;	
	private Label scoreLabel, highscoreLabel;
	private Button surrenderButton, cheatButton;
	
	public ScorePanel(Game game) {		
		setPreferredSize(SIZE);
		setOpaque(false);
		
		try {
			throphyImage = ImageIO.read(new File("res/trophy.png"));
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
	
		cheatButton = (Button) add(new Button(PHRASE_CHEAT));
		cheatButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.onCheatButtonClicked();
			}
		});
		
		scoreLabel = (Label) add(new Label("0"));
		add(SPACING);
		highscoreLabel = (Label) add(new Label("0"));
		
		surrenderButton = (Button) add(new Button(PHRASE_SURRENDER));
		surrenderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.onSurrenderButtonClicked();
				setButtonsEnabled(false);
			}
		});
		
		game.setScoreObserver(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(throphyImage, IMAGE_X, IMAGE_Y, IMAGE_SIZE, IMAGE_SIZE, null);
	}

	@Override
	public void onScoreChanged(int score) {
		scoreLabel.setText("" + score);
	}

	@Override
	public void onHighscoreChanged(int highscore) {
		highscoreLabel.setText("" + highscore);		
	}
	
	public void setButtonsEnabled(boolean enabled) {
		cheatButton.setEnabled(enabled);
		surrenderButton.setEnabled(enabled);
	}

	@Override
	public void onCloseGameOver() {
		setButtonsEnabled(true);		
	}

}
