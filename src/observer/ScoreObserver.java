package observer;

public interface ScoreObserver {
	
	public void onScoreChanged(int score);
	public void onHighscoreChanged(int highscore);
	public void onCloseGameOver();
}
