package homework.puzzle.Services;

import homework.puzzle.Models.Puzzle;

public interface ITileService {
    boolean moveTileUp(Puzzle puzzle, int swapTile);

    boolean moveTileDown(Puzzle puzzle, int swapTile);

    boolean moveTileLeft(Puzzle puzzle, int swapTile);

    boolean moveTileRight(Puzzle puzzle, int swapTile);
}
