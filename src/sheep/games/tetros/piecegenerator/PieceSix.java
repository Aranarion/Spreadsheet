package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the sixth type of tetros piece.
 * This piece consists of four cells forming an L shape.
 */
public class PieceSix implements PieceGenerator {
    /**
     * Generates the cells for the sixth type of tetros piece.
     * The generated piece consists of four cells forming an L shape.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list is not null.
     * @post The contents list contains four cell locations representing the generated tetros piece.
     *       The cells are arranged to form an L shape.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(0, 2));
        contents.add(new CellLocation(1, 1));
        contents.add(new CellLocation(0, 1));
    }

    /**
     * Generates the falling type for the sixth type of tetros piece.
     * This method returns the identifier for the sixth type of tetros piece.
     *
     * @return The falling type identifier for the sixth type of tetros piece.
     * @post The returned value is the identifier for the sixth type of tetros piece (2).
     */
    @Override
    public int generateFallingType() {
        return 2;
    }
}