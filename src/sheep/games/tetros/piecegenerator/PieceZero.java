package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public class PieceZero implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents, int fallingType) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(1, 2));
        fallingType = 4;
    }
}
