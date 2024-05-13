package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the fifth type of tetros piece.
 * This piece consists of four cells arranged vertically.
 */
public class PieceFive implements PieceGenerator {
    /**
     * Generates the cells for the fifth type of tetros piece.
     * The generated piece consists of four cells arranged vertically.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list is not null.
     * @post The contents list contains four cell locations representing the generated tetros piece.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(2, 0));
        contents.add(new CellLocation(3, 0));
    }

    /**
     * Generates the falling type for the fifth type of tetros piece.
     * This method returns the identifier for the fifth type of tetros piece.
     *
     * @return The falling type identifier for the fifth type of tetros piece.
     * @post The returned value is the identifier for the fifth type of tetros piece (6).
     */
    @Override
    public int generateFallingType() {
        return 6;
    }
}