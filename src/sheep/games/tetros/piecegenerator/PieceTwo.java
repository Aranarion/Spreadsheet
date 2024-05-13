package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the second type of tetros piece.
 * This piece consists of four cells forming a mirrored L shape.
 */
public class PieceTwo implements PieceGenerator {
    /**
     * Generates the cells for the second type of tetros piece.
     * The generated piece consists of four cells forming a mirrored L shape.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list is not null.
     * @post The contents list contains four cell locations representing the generated tetros piece.
     *       The cell locations form a mirrored L shape.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(2, 1));
        contents.add(new CellLocation(2, 0));
    }

    /**
     * Generates the falling type for the second type of tetros piece.
     * This method returns the identifier for the second type of tetros piece.
     *
     * @return The falling type identifier for the second type of tetros piece.
     * @post The returned value is the identifier for the second type of tetros piece (5).
     */
    @Override
    public int generateFallingType() {
        return 5;
    }
}
