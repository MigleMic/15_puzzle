package homework.puzzle.Services;

import homework.puzzle.Models.Puzzle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PuzzleService implements IPuzzleService {

    //-1 is the representation of an empty tile
    private static final int[][] answer = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, -1}};

    @Override
    public Puzzle newPuzzle() {
        int[][] puzzle = createPuzzle(answer);

        shuffleBoard(puzzle);

        return new Puzzle(puzzle);
    }

    private int[][] createPuzzle(int[][] originalPuzzle) {
        int rows = originalPuzzle.length;
        int columns = originalPuzzle[0].length;

        int[][] puzzle = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            puzzle[i] = Arrays.copyOf(originalPuzzle[i], rows);
        }

        return puzzle;
    }

    public static void swapTiles(int[][] boardPuzzle, int emptyTileRow, int emptyTileColumn, int swapTileRow, int swapTileColumn) {
        int temp = boardPuzzle[emptyTileRow][emptyTileColumn];

        boardPuzzle[emptyTileRow][emptyTileColumn] = boardPuzzle[swapTileRow][swapTileColumn];
        boardPuzzle[swapTileRow][swapTileColumn] = temp;
    }

    public boolean puzzleIsSolved(Puzzle puzzle) {
        int[][] boardPuzzle = puzzle.getPuzzle();

        return Arrays.deepEquals(boardPuzzle, answer);
    }

    public void shuffleBoard(int[][] puzzle) {
        do {
            shuffleBoardValues(puzzle);
        }
        while (!puzzleIsSolvable(puzzle));
    }

    private void shuffleBoardValues(int[][] puzzle) {
        int rows = puzzle.length;
        int columns = puzzle[0].length;

        List<Integer> numberedTiles = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (puzzle[i][j] != -1) {
                    numberedTiles.add(puzzle[i][j]);
                }
            }
        }

        Collections.shuffle(numberedTiles);

        int indx = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (puzzle[i][j] != -1) {
                    puzzle[i][j] = numberedTiles.get(indx);
                    indx++;
                }
            }
        }
    }

    private boolean puzzleIsSolvable(int[][] puzzle) {
        int size = puzzle.length;

        int[] oneDimensionPuzzle = new int[size * size];
        int indx = 0;

        //Conversion of an array
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                oneDimensionPuzzle[indx++] = puzzle[i][j];
            }
        }

        int inversions = getInversions(oneDimensionPuzzle);

        if (size % 2 == 1) {
            return inversions % 2 == 0;
        } else {
            int blankTile = findEmptyTile(puzzle);
            if ((size - blankTile) % 2 == 0)
                return inversions % 2 == 1;
            else
                return inversions % 2 == 0;
        }
    }

    private int getInversions(int[] puzzle) {
        int inversions = 0;
        int size = puzzle.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (puzzle[i] != -1 && puzzle[j] != -1 && puzzle[i] > puzzle[j])
                    inversions++;
            }
        }
        return inversions;
    }

    public int findEmptyTile(int[][] puzzle) {
        int size = puzzle.length;

        for (int i = size - 1; i >= 0; i--) {
            for (int j = size - 1; j >= 0; j--) {
                if (puzzle[i][j] == -1) {
                    return i + 1;
                }
            }
        }
        //returns -1 if the empty tile is not in the puzzle
        return -1;
    }
}