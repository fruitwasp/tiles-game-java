package observer;

import model.Dock.PuzzleBlock;

public interface DockObserver {

	public void onDockLoaded(PuzzleBlock[] puzzleBlocks);
	public void onPuzzleBlockPlaced(int index);
}
