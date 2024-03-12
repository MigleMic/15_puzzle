package homework.puzzle.Models;

public class Puzzle {
    private int[][] puzzle;

    public Puzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle() {
    }

    public int[][] getPuzzle() {
        return puzzle;
    }
}
