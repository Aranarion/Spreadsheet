package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs tetros piece 2
 */
public class PieceTwo implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(2, 1));
        contents.add(new CellLocation(2, 0));
    }

    @Override
    public int generateFallingType() {
        return 5;
    }
}
