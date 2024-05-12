package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public class PieceFour implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(1, 1));
    }
    @Override
    public int generateFallingType() {
        return 3;
    }
}
