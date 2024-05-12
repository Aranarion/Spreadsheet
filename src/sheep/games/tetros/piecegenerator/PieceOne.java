package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs tetros piece 1
 */
public class PieceOne implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(2, 0));
        contents.add(new CellLocation(2, 1));
    }

    @Override
    public int generateFallingType() {
        return 7;
    }
}
