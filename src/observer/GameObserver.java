package observer;

import model.Dock.PuzzleBlock;

public interface GameObserver {

	public void onGridLoaded(PuzzleBlock[][] blocks);
	public void onDockLoaded(PuzzleBlock[] puzzleBlocks);
}
