package sheep.games.tetros;

import sheep.games.random.RandomTile;
import sheep.games.tetros.piecegenerator.*;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;

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
    public TetrosTileCreator(Tetros tetros, Sheet sheet, Render renderer, RandomTile randomTile) {
        this.tetros = tetros;
        this.sheet = sheet;
        this.renderer = renderer;
        this.randomTile = randomTile;
    }
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
