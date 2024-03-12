package homework.puzzle.Controllers;

import homework.puzzle.DTOs.*;
import homework.puzzle.Models.*;
import homework.puzzle.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/puzzles")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDTO> createGameBoard() {
        BoardDTO newBoard = boardService.startNewBoard();

        return new ResponseEntity<>(newBoard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllGameBoards() {
        List<BoardDTO> allBoards = boardService.getAllBoards();

        return new ResponseEntity<>(allBoards, HttpStatus.OK);
    }

    @GetMapping("/{gameID}")
    public ResponseEntity<BoardDTO> getBoardByID(@PathVariable int gameID) {
        Puzzle puzzle = boardService.getBoard(gameID);

        if (puzzle != null) {
            BoardDTO boardDTO = new BoardDTO(gameID, puzzle.getPuzzle());

            return new ResponseEntity<>(boardDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/solved/{gameID}")
    public ResponseEntity<Void> solvedBoard(@PathVariable int gameID) {
        Puzzle puzzle = boardService.getBoard(gameID);

        if (puzzle != null) {
            PuzzleService puzzleService = new PuzzleService();

            boolean solved = puzzleService.puzzleIsSolved(puzzle);

            if (solved) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{gameID}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int gameID) {
        boolean deletedBoard = boardService.deleteBoard(gameID);

        return deletedBoard ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}