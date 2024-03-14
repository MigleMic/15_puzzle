package homework.puzzle.Services;

import homework.puzzle.DTOs.TileDTO;
import homework.puzzle.Models.Puzzle;
import org.springframework.stereotype.Service;

@Service
public class TileService implements ITileService {
    //When needed, additional logic can be put down in the functions
    @Override
    public boolean moveTileUp(Puzzle puzzle, TileDTO tileDTO) {
        return moveTile(puzzle, tileDTO.getMoveDirection(), tileDTO.getSwapTile());
    }

    @Override
    public boolean moveTileDown(Puzzle puzzle, TileDTO tileDTO) {
        return moveTile(puzzle, tileDTO.getMoveDirection(), tileDTO.getSwapTile());
    }

    @Override
    public boolean moveTileLeft(Puzzle puzzle, TileDTO tileDTO) {
        return moveTile(puzzle, tileDTO.getMoveDirection(), tileDTO.getSwapTile());
    }

    @Override
    public boolean moveTileRight(Puzzle puzzle, TileDTO tileDTO) {
        return moveTile(puzzle, tileDTO.getMoveDirection(), tileDTO.getSwapTile());
    }

    private boolean moveTile(Puzzle puzzle, String direction, int swapTile) {
        int[][] boardPuzzle = puzzle.getPuzzle();
        int size = boardPuzzle.length;

        int[] emptyTile = findEmptyTile(boardPuzzle);
        int row = emptyTile[0];
        int column = emptyTile[1];

        switch (direction) {
            case "UP":
                //must not be in the highest row and I can switch the empty tile with the upper one
                if (row > 0 && boardPuzzle[row - 1][column] == swapTile) {
                    PuzzleService.swapTiles(boardPuzzle, row, column, row - 1, column);
                    return true;
                }
                break;
            case "DOWN":
                //must not be in the lowest row and I can switch the empty tile with the lower one
                if (row < size - 1 && boardPuzzle[row + 1][column] == swapTile) {
                    PuzzleService.swapTiles(boardPuzzle, row, column, row + 1, column);
                    return true;
                }
                break;
            case "RIGHT":
                //must not be in the rightest corner and I can switch the empty tile with the right one
                if (column < size - 1 && boardPuzzle[row][column + 1] == swapTile) {
                    PuzzleService.swapTiles(boardPuzzle, row, column, row, column + 1);
                    return true;
                }
                break;
            case "LEFT":
                //must not be in the leftest corner and I can switch the empty tile with the left one
                if (column > 0 && boardPuzzle[row][column - 1] == swapTile) {
                    PuzzleService.swapTiles(boardPuzzle, row, column, row, column - 1);
                    return true;
                }
                break;
        }

        return false;
    }

    private int[] findEmptyTile(int[][] puzzle) {
        int[] coordinates = new int[2];
        int size = puzzle.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle[i][j] == -1) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }

        //In case the empty tile was not found
        coordinates[0] = -1;
        coordinates[1] = -1;

        return coordinates;
    }
}