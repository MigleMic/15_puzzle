package homework.puzzle;

import homework.puzzle.Models.Puzzle;
import homework.puzzle.Services.TileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TileServiceTest {

    @Autowired
    private TileService tileService;

    @Test
    public void shouldMoveTileUpReturnTrue() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {2, 8, 9, 1},
                {3, 15, 7, 6},
                {13, 5, 11, 14},
                {12, 10, 4, -1}});

        Puzzle swappedPuzzle = new Puzzle(new int[][]{
                {2, 8, 9, 1},
                {3, 15, 7, 6},
                {13, 5, 11, -1},
                {12, 10, 4, 14}});

        assertTrue(tileService.moveTileUp(puzzle, 14), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileUpReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {2, 8, 9, 1},
                {3, 15, 7, 6},
                {13, 5, 11, 14},
                {12, 10, 4, -1}});

        assertFalse(tileService.moveTileUp(puzzle, 1), "Should not be possible to swap");
    }

    @Test
    public void shouldMoveTileDownReturnTrue() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        Puzzle swappedPuzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});

        assertTrue(tileService.moveTileDown(puzzle, 12), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileDownReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        assertFalse(tileService.moveTileDown(puzzle, 7), "Should not be possible to swap");
    }

    @Test
    public void shouldMoveTileLeftReturnTrue() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, -1, 11},
                {13, 14, 15, 12}});

        Puzzle swappedPuzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, -1, 10, 11},
                {13, 14, 15, 12}});

        assertTrue(tileService.moveTileLeft(puzzle, 10), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileLeftReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, -1, 11},
                {13, 14, 15, 12}});

        assertFalse(tileService.moveTileLeft(puzzle, 13), "Should not be possible to swap");
    }

    @Test
    public void shouldMoveTileRightReturnTrue() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, -1, 11},
                {13, 14, 15, 12}});

        Puzzle swappedPuzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        assertTrue(tileService.moveTileRight(puzzle, 11), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileRightReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        assertFalse(tileService.moveTileRight(puzzle, 3), "Should not be possible to swap");
    }
}