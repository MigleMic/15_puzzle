package homework.puzzle;

import homework.puzzle.DTOs.TileDTO;
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

        TileDTO tileDTO = new TileDTO("UP", 14);
        assertTrue(tileService.moveTileUp(puzzle, tileDTO), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileUpReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {2, 8, 9, 1},
                {3, 15, 7, 6},
                {13, 5, 11, 14},
                {12, 10, 4, -1}});

        TileDTO tileDTO = new TileDTO("UP", 1);

        assertFalse(tileService.moveTileUp(puzzle, tileDTO), "Should not be possible to swap");
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

        TileDTO tileDTO = new TileDTO("DOWN", 12);

        assertTrue(tileService.moveTileDown(puzzle, tileDTO), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileDownReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        TileDTO tileDTO = new TileDTO("DOWN", 7);

        assertFalse(tileService.moveTileDown(puzzle, tileDTO), "Should not be possible to swap");
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

        TileDTO tileDTO = new TileDTO("LEFT", 10);

        assertTrue(tileService.moveTileLeft(puzzle, tileDTO), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileLeftReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, -1, 11},
                {13, 14, 15, 12}});

        TileDTO tileDTO = new TileDTO("LEFT", 13);

        assertFalse(tileService.moveTileLeft(puzzle, tileDTO), "Should not be possible to swap");
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

        TileDTO tileDTO = new TileDTO("RIGHT", 11);

        assertTrue(tileService.moveTileRight(puzzle, tileDTO), "Should be possible to swap");
        assertArrayEquals(swappedPuzzle.getPuzzle(), puzzle.getPuzzle(), "Puzzles should match");
    }

    @Test
    public void shouldMoveTileRightReturnFalse() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, -1},
                {13, 14, 15, 12}});

        TileDTO tileDTO = new TileDTO("RIGHT", 3);

        assertFalse(tileService.moveTileRight(puzzle, tileDTO), "Should not be possible to swap");
    }
}