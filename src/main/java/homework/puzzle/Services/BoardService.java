package homework.puzzle.Services;

import homework.puzzle.DTOs.BoardDTO;
import homework.puzzle.Models.Puzzle;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoardService implements IBoardService {
    private final PuzzleService puzzleService;
    //Stores the game ID and the puzzle connected to it
    private final HashMap<Integer, Puzzle> puzzles;

    private int gameIDCounter = 1;

    public BoardService(PuzzleService puzzleService, HashMap<Integer, Puzzle> puzzles) {
        this.puzzleService = puzzleService;
        this.puzzles = puzzles;
    }

    @Override
    public BoardDTO startNewBoard() {
        int gameID = newGameID();

        Puzzle puzzle = puzzleService.newPuzzle();
        puzzles.put(gameID, puzzle);

        return new BoardDTO(gameID, puzzle.getPuzzle());
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        return puzzles.entrySet().stream().map(puzzleEntry ->{
            int gameID = puzzleEntry.getKey();
            Puzzle puzzle = puzzleEntry.getValue();

            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setGameID(gameID);
            boardDTO.setPuzzle(puzzle.getPuzzle());

            return boardDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Puzzle getBoard(int gameID) {
        return puzzles.get(gameID);
    }

    @Override
    public boolean deleteBoard(int gameID) {
        return puzzles.remove(gameID) != null;
    }

    private int newGameID() {
        return gameIDCounter++;
    }
}