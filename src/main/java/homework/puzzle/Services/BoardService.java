package homework.puzzle.Services;

import homework.puzzle.DTOs.BoardDTO;
import homework.puzzle.Models.Puzzle;
import org.springframework.stereotype.Service;

import java.util.*;

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

        BoardDTO boardDTO = new BoardDTO(gameID, puzzle.getPuzzle());

        return boardDTO;
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> allBoards = new ArrayList<>();

        for (Map.Entry<Integer, Puzzle> puzzleEntry : puzzles.entrySet()) {
            int gameID = puzzleEntry.getKey();
            Puzzle puzzle = puzzleEntry.getValue();

            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setGameID(gameID);
            boardDTO.setPuzzle(puzzle.getPuzzle());

            allBoards.add(boardDTO);
        }

        return allBoards;
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
