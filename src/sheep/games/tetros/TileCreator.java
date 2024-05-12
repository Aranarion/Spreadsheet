package sheep.games.tetros;

import sheep.games.random.RandomTile;
import sheep.games.tetros.piecegenerator.*;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import javax.swing.*;
import java.util.ArrayList;

public class TileCreator {
    private Tetros tetros;
    private Sheet sheet;
    private Render renderer;
    private RandomTile randomTile;
    private final PieceGenerator[] pieceGenerators = {
            new PieceZero(),
            new PieceOne(),
            new PieceTwo(),
            new PieceThree(),
            new PieceFour(),
            new PieceFive(),
            new PieceSix()
    };
    public TileCreator(Tetros tetros, Sheet sheet,Render renderer,RandomTile randomTile) {
        this.tetros = tetros;
        this.sheet = sheet;
        this.renderer = renderer;
        this.randomTile = randomTile;
    }
    public boolean drop() {
        tetros.newContents(new ArrayList<>());
        newPiece();
        for (CellLocation location : tetros.accessContents()) {
            if (!sheet.valueAt(location).render().equals("")) {
                return true;
            }
        }
        renderer.ununrender(tetros.accessContents());

        return false;
    }

    private void newPiece() {
        int value = randomTile.pick();
        pieceGenerators[value].generatePiece(tetros.accessContents());
        tetros.updateFallingType(pieceGenerators[value].generateFallingType());
    }
}
