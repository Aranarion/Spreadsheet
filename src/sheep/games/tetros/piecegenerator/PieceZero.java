package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the first type of tetros piece.
 * This piece consists of four cells forming a square shape.
 */
public class PieceZero implements PieceGenerator {

    /**
     * Generates the cells for the first type of tetros piece.
     * The generated piece consists of four cells forming a square shape.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list must not be null.
     * @post The contents list contains four cell locations representing the generated tetros piece.
     *       The cell locations form a square shape.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(1, 2));
    }

    /**
     * Generates the falling type for the first type of tetros piece.
     * This method returns the identifier for the first type of tetros piece.
     *
     * @return The falling type identifier for the first type of tetros piece.
     * @post The returned value is the identifier for the first type of tetros piece (4).
     */
    @Override
    public int generateFallingType() {
        return 4;
    }
}
