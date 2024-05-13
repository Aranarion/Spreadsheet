package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Constructs the fourth type of Tetris piece.
 * This piece consists of four cells arranged in a square.
 */
public class PieceFour implements PieceGenerator {
    /**
     * Generates the cells for the fourth type of Tetris piece.
     * The generated piece consists of four cells arranged in a square shape.
     *
     * @param contents The list to which the generated cell locations are added.
     * @pre The contents list is not null.
     * @post The contents list contains four cell locations representing the generated Tetris piece.
     *       The cells are arranged in a square shape.
     */
    @Override
    public void generatePiece(List<CellLocation> contents) {
        contents.add(new CellLocation(0, 0));
        contents.add(new CellLocation(0, 1));
        contents.add(new CellLocation(1, 0));
        contents.add(new CellLocation(1, 1));
    }

    /**
     * Generates the falling type for the fourth type of tetros piece.
     * This method returns the identifier for the fourth type of tetros piece.
     *
     * @return The falling type identifier for the fourth type of tetros piece.
     * @post The returned value is the identifier for the fourth type of tetros piece (3).
     */
    @Override
    public int generateFallingType() {
        return 3;
    }
}
