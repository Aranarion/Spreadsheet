package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Interface for generating tetros pieces.
 * Implementations of this interface provide methods to specify the cells of a tetros piece
 * and determine its falling type.
 */
public interface PieceGenerator {
    /**
     * Specifies the cells at which the piece should be placed when initially dropped.
     *
     * @param contents The current contents of the tetros game board.
     * @pre The contents list is not null.
     * @post The contents list contains the cell locations of the generated tetros piece.
     *       The generated piece is added to the contents list.
     */
    void generatePiece(List<CellLocation> contents);

    /**
     * Determines the falling type of the tetros piece.
     *
     * @return The falling type identifier to be displayed in the cells of the tetros piece.
     * @post The returned value represents the falling type of the tetros piece.
     *       It is used to determine the appearance of the piece on the game board.
     */
    int generateFallingType();
}
