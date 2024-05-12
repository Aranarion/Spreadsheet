package sheep.games.tetros.piecegenerator;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Creates possible tetros pieces to use
 */
public interface PieceGenerator {
    /**
     * Specifies the cells at which the piece should be placed when intially dropped
     * @param contents the current contents of the pieces of the tetros game
     */
    void generatePiece(List<CellLocation> contents);

    /**
     * Determines the falling type
     * @return Displays this in the cells of the tetros piece
     */
    int generateFallingType();
}
