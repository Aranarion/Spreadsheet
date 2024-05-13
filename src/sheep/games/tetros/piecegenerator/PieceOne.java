package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the first type of tetros piece.
 * This piece consists of four cells forming an L shape.
 */
public class PieceOne implements PieceGenerator {
    /**
     * Generates the cells for the first type of tetros piece.
     * The generated piece consists of four cells forming an L shape.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list is not null.
     * @post The contents list contains four cell locations representing the generated tetros piece.
     *       The cells are arranged to form an L shape.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(2, 0));
        contents.add(new CellLocation(2, 1));
    }

    /**
     * Generates the falling type for the first type of tetros piece.
     * This method returns the identifier for the first type of tetros piece.
     *
     * @return The falling type identifier for the first type of tetros piece.
     * @post The returned value is the identifier for the first type of tetros piece (7).
     */
    @Override
    public int generateFallingType() {
        return 7;
    }
}
