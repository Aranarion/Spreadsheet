package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public class PieceOne implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents, int fallingType) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(2, 0));
        contents.add(new CellLocation(2, 1));
        fallingType = 7;
    }
}
