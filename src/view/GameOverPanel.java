package view;

import helper.Button;
import helper.Label;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;

import model.Game;

@SuppressWarnings("serial")
public class GameOverPanel extends JPanel {
	
	private Game game;

	private Label reasonLabel, highscoreLabel;
	
	private JPanel infoPanel;	
	private Button cancelButton, retryButton;
	
	private static final Dimension BUTTON_SMALL_SIZE = new Dimension(170, 100), PANEL_SIZE = new Dimension(200, 400);
	private static final String PHRASE_CANCEL = "Cancel", PHRASE_RETRY = "Retry", PHRASE_NO_MOVES_LEFT = "No moves left.", PHRASE_YOU_SURRENDERED = "You surrendered";
	
	public GameOverPanel(Game game, boolean surrendered) {
		setLayout(new BorderLayout());
		setMinimumSize(PANEL_SIZE);
		setPreferredSize(PANEL_SIZE);
		
		this.game = game;
		
		reasonLabel = new Label(surrendered ? PHRASE_YOU_SURRENDERED : PHRASE_NO_MOVES_LEFT);
		highscoreLabel = new Label(Integer.toString(game.getHighScore()));
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(reasonLabel, BorderLayout.NORTH);
		infoPanel.add(highscoreLabel, BorderLayout.SOUTH);
		add(infoPanel, BorderLayout.NORTH);
		
		cancelButton = new Button(PHRASE_CANCEL);
		cancelButton.setBackground(Color.GREEN);
		cancelButton.setPreferredSize(BUTTON_SMALL_SIZE);
		cancelButton.setMinimumSize(BUTTON_SMALL_SIZE);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				game.onCancelGameOverButtonClicked();
			}
		});
		add(cancelButton, BorderLayout.CENTER);
		
		retryButton = new Button(PHRASE_RETRY);
		retryButton.setBackground(Color.ORANGE);
		retryButton.setPreferredSize(BUTTON_SMALL_SIZE);
		retryButton.setMinimumSize(BUTTON_SMALL_SIZE);
		retryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				game.onRetryButtonClicked();
			}
		});
		add(retryButton, BorderLayout.SOUTH);
		
		add(Box.createRigidArea(new Dimension(333, 100)));
	}
	
	public void setSurrendered(boolean surrendered) {
		reasonLabel.setText(surrendered ? PHRASE_YOU_SURRENDERED : PHRASE_NO_MOVES_LEFT);
		cancelButton.setEnabled(surrendered);
		
		revalidate();
	}
	
	public void setHighscore(int highscore) {
		highscoreLabel.setText("" + highscore);
		
		revalidate();
	}
}
