package homework.puzzle.DTOs;

public class BoardDTO {
    private int gameID;
    private int[][] puzzle;

    public BoardDTO(int gameID, int[][] puzzle) {
        this.gameID = gameID;
        this.puzzle = puzzle;
    }

    public BoardDTO() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }
}