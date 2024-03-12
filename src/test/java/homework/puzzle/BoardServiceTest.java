package homework.puzzle;

import homework.puzzle.DTOs.BoardDTO;
import homework.puzzle.Models.Puzzle;
import homework.puzzle.Services.BoardService;
import homework.puzzle.Services.PuzzleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BoardServiceTest {

    @Mock
    private PuzzleService puzzleService;

    @Test
    public void shouldStartNewBoard() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});

        when(puzzleService.newPuzzle()).thenReturn(puzzle);

        BoardService boardService = new BoardService(puzzleService, new HashMap<>());
        BoardDTO boardDTO = boardService.startNewBoard();
        puzzleService.newPuzzle();

        assertNotNull(boardDTO, "Board should not be null");
        assertTrue(boardDTO.getGameID() > 0, "Game ID should be positive");
        assertNotNull(boardDTO.getPuzzle(), "Puzzle should not be empty");
    }

    @Test
    public void shouldGetAllBoards() {
        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});

        HashMap<Integer, Puzzle> allGames = new HashMap<>();

        assertTrue(allGames.isEmpty(), "List should be empty");

        allGames.put(1, puzzle);
        allGames.put(2, puzzle);
        allGames.put(3, puzzle);

        BoardService boardService = new BoardService(puzzleService, allGames);

        List<BoardDTO> boardDTOS = boardService.getAllBoards();

        assertEquals(allGames.size(), boardDTOS.size(), "Puzzle list size should be equal");
    }

    @Test
    public void shouldGetBoard() {
        int gameID = 1;
        HashMap<Integer, Puzzle> allPuzzles = new HashMap<>();

        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});

        allPuzzles.put(gameID, puzzle);

        BoardService boardService = new BoardService(puzzleService, allPuzzles);

        Puzzle puzzle1 = boardService.getBoard(gameID);

        assertNotNull(puzzle1, "Puzzle should not be null");
        assertArrayEquals(puzzle.getPuzzle(), puzzle1.getPuzzle(), "Puzzles should be equal");

        assertNotNull(allPuzzles.get(gameID), "Puzzle with such ID should exist");
        assertNull(allPuzzles.get(20), "Puzzle with such ID should not exist");
    }

    @Test
    public void shouldDeleteBoard() {
        int gameID = 1;
        HashMap<Integer, Puzzle> allPuzzles = new HashMap<>();

        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});
        allPuzzles.put(gameID, puzzle);

        BoardService boardService = new BoardService(puzzleService, allPuzzles);

        boolean deleted = boardService.deleteBoard(gameID);

        assertTrue(deleted, "Puzzle should be deleted");
        assertNull(allPuzzles.get(gameID), "Puzzle was not removed from all puzzles");
    }

    @Test
    public void shouldNotDeleteBoard(){
        int invalidGameID = 10;
        HashMap<Integer, Puzzle> allPuzzles = new HashMap<>();

        Puzzle puzzle = new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, -1}});
        allPuzzles.put(1, puzzle);

        BoardService boardService = new BoardService(puzzleService, allPuzzles);

        boolean deleted = boardService.deleteBoard(invalidGameID);

        assertFalse(deleted, "Puzzle with such ID does not exist, should not be deleted");

        assertNotNull(allPuzzles.get(1), "Puzzle should not been removed");
    }
}