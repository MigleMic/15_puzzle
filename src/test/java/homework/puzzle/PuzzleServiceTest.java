package homework.puzzle;

import homework.puzzle.Models.Puzzle;
import homework.puzzle.Services.PuzzleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PuzzleServiceTest {

    @Autowired
    private PuzzleService puzzleService;

    @Test
    public void shouldFindEmptyTile() {
        int[][] puzzle = {
                {12, 1, 10, 14},
                {7, 11, 4, 8},
                {5, 9, 15, -1},
                {13, 6, 3, 2}};

        int emptyTile = 3;
        int actualPlace = puzzleService.findEmptyTile(puzzle);

        assertEquals(emptyTile, actualPlace, "Empty tile should be in the specified position");
    }

    @Test
    public void shouldNotFindEmptyTile() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {6, 13, 7, 10},
                {8, 9, 11, 16},
                {15, 2, 12, 5},
                {14, 3, 1, 4}});

        int emptyTile = -1;
        int actualPlace = puzzleService.findEmptyTile(puzzle.getPuzzle());

        assertEquals(emptyTile, actualPlace, "Empty tile should be missing in the puzzle");
    }

    @Test
    public void shouldPuzzleIsSolvedReturnTrue() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});

        assertTrue(puzzleService.puzzleIsSolved(puzzle), "Puzzle should be solveable");
    }

    @Test
    public void shouldPuzzleIsSolvedReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {12, 1, 10, 2},
                {7, 11, 4, 14},
                {5, -1, 9, 15},
                {8, 13, 6, 3}});

        assertFalse(puzzleService.puzzleIsSolved(puzzle), "Puzzle should not be solveable");
    }
}