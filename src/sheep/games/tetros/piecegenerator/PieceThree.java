package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public class PieceThree implements PieceGenerator {
    @Override
    public void generatePiece(List<CellLocation> contents, int fallingType) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(0, 2));
        contents.add(new CellLocation(1, 1));
        fallingType = 8;
    }
}
