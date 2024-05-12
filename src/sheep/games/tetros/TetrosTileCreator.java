package sheep.games.tetros;

import sheep.games.random.RandomTile;
import sheep.games.tetros.piecegenerator.*;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;

/**
 * Creates and initialises a new tetros tile when required
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
     * Constructs the tile creator
     * @param tetros the current iteration of tetros
     * @param sheet the current iteration of the spreadsheet
     * @param renderer the current chosen renderer
     * @param randomTile the randomtile implementation chosen
     */
    public TetrosTileCreator(Tetros tetros, Sheet sheet, Render renderer, RandomTile randomTile) {
        this.tetros = tetros;
        this.sheet = sheet;
        this.renderer = renderer;
        this.randomTile = randomTile;
    }

    /**
     *Drops a new piece onto the gameboard
     * @return true if the piece immediately collides with another piece and game over
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
