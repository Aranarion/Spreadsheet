package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

public interface PieceGenerator {
    void generatePiece(List<CellLocation> contents);
    int generateFallingType();
}
