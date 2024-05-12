package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public class PieceTwo implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents, int fallingType) {
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(2, 1));
        contents.add(new CellLocation(2, 0));
        fallingType = 5;
    }
}
