package homework.puzzle.Controllers;

import homework.puzzle.DTOs.TileDTO;
import homework.puzzle.Models.Puzzle;
import homework.puzzle.Services.BoardService;
import homework.puzzle.Services.TileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tiles")
public class TileController {
    private final TileService tileService;
    private final BoardService boardService;

    @Autowired
    public TileController(TileService tileService, BoardService boardService) {
        this.tileService = tileService;
        this.boardService = boardService;
    }

    @PostMapping("/moveTile/{gameID}")
    public ResponseEntity<Puzzle> moveTile(@PathVariable int gameID, @RequestBody TileDTO tileDTO) {
        Puzzle puzzle = boardService.getBoard(gameID);

        boolean returnedValue = false;

        if (puzzle != null) {
            int swapTiles = tileDTO.getSwapTile();

            switch (tileDTO.getMoveDirection()) {
                case "UP":
                    returnedValue = tileService.moveTileUp(puzzle, swapTiles);
                    break;
                case "DOWN":
                    returnedValue = tileService.moveTileDown(puzzle, swapTiles);
                    break;
                case "RIGHT":
                    returnedValue = tileService.moveTileRight(puzzle, swapTiles);
                    break;
                case "LEFT":
                    returnedValue = tileService.moveTileLeft(puzzle, swapTiles);
                    break;
            }

            if (returnedValue != false) {
                return new ResponseEntity<>(puzzle, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}