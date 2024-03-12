package homework.puzzle.DTOs;

public class TileDTO {
    private String moveDirection;
    private int swapTile;

    public TileDTO(String moveDirection, int swapTile) {
        this.moveDirection = moveDirection;
        this.swapTile = swapTile;
    }

    public TileDTO() {
    }

    public String getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(String moveDirection) {
        this.moveDirection = moveDirection;
    }

    public int getSwapTile() {
        return swapTile;
    }

    public void setSwapTile(int swapTile) {
        this.swapTile = swapTile;
    }
}