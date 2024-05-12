package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs tetros piece 0
 */
public class PieceZero implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(1, 2));
    }

    @Override
    public int generateFallingType() {
        return 4;
    }
}
