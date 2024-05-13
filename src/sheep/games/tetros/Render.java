package sheep.games.tetros;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Interface for rendering tetros pieces.
 * Implementations of this interface provide methods to unrender pieces from their old position
 * and render them at their new positions.
 */
public interface Render {

    /**
     * Removes tetros pieces from their old positions.
     *
     * @param items The list of tetros pieces to be removed.
     * @pre The items list must not be null.
     * @post Tetros pieces are removed from their old positions.
     *       The items list no longer contains the tetros pieces.
     */
    void unrender(List<CellLocation> items);

    /**
     * Renders tetros pieces in their new updated positions.
     *
     * @param items The list of tetros pieces to be rendered.
     * @pre The items list must not be null.
     * @post Tetros pieces are rendered at their new positions.
     *       The items list contains the tetros pieces at their new positions.
     */
    void render(List<CellLocation> items);
}
