package sheep.games.tetros;

import sheep.games.random.RandomTile;
import sheep.games.tetros.piecegenerator.*;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;

/**
 * Creates and initializes a new tetros tile when required.
 * This class is responsible for generating and dropping tetros pieces onto the game board.
 */
public class TetrosTileCreator implements TileCreator {
    private final Tetros tetros;
    private final Sheet sheet;
    private final Render renderer;
    private final RandomTile randomTile;
    private final PieceGenerator[] pieceGenerators = {
        new PieceZero(),
        new PieceOne(),
        new PieceTwo(),
        new PieceThree(),
        new PieceFour(),
        new PieceFive(),
        new PieceSix()
    };

    /**
     * Constructs the tetros tile creator.
     *
     * @param tetros     The current instance of tetros.
     * @param sheet      The current iteration of the spreadsheet.
     * @param renderer   The current chosen renderer.
     * @param randomTile The implementation of RandomTile used for picking random tiles.
     * @pre tetros != null && sheet != null && renderer != null && randomTile != null
     * @post A new tetros tile creator instance is created with the provided parameters.
     */
    public TetrosTileCreator(Tetros tetros, Sheet sheet, Render renderer, RandomTile randomTile) {
        this.tetros = tetros;
        this.sheet = sheet;
        this.renderer = renderer;
        this.randomTile = randomTile;
    }

    /**
     * Drops a new piece onto the game board.
     *
     * @return True if the piece immediately collides with another piece, indicating game over.
     * @pre tetros != null && sheet != null
     * @post A new tetros piece is dropped onto the game board, and collision detection is performed.
     */
    public boolean drop() {
        tetros.newContents(new ArrayList<>());
        newPiece();
        for (CellLocation location : tetros.accessContents()) {
            if (!sheet.valueAt(location).render().isEmpty()) {
                return true;
            }
        }
        renderer.render(tetros.accessContents());

        return false;
    }

    private void newPiece() {
        int value = randomTile.pick();
        pieceGenerators[value].generatePiece(tetros.accessContents());
        tetros.updateFallingType(pieceGenerators[value].generateFallingType());
    }
}
