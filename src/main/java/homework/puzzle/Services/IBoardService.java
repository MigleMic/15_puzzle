package homework.puzzle.Services;

import homework.puzzle.DTOs.BoardDTO;
import homework.puzzle.Models.Puzzle;

import java.util.List;

public interface IBoardService {
    List<BoardDTO> getAllBoards();

    Puzzle getBoard(int gameID);

    boolean deleteBoard(int gameID);

    BoardDTO startNewBoard();
}
