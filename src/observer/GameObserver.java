package observer;

import model.Dock.PuzzleBlock;

public interface GameObserver {

	public void onGridLoaded(char[][] blocks);
	public void onDockLoaded(PuzzleBlock[] puzzleBlocks);
}
