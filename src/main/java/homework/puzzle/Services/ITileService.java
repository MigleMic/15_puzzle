package homework.puzzle.Services;

import homework.puzzle.DTOs.TileDTO;
import homework.puzzle.Models.Puzzle;

public interface ITileService {
    boolean moveTileUp(Puzzle puzzle, TileDTO tileDTO);

    boolean moveTileDown(Puzzle puzzle, TileDTO tileDTO);

    boolean moveTileLeft(Puzzle puzzle, TileDTO tileDTO);

    boolean moveTileRight(Puzzle puzzle, TileDTO tileDTO);
}
