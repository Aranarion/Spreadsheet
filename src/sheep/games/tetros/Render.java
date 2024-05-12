package sheep.games.tetros;

import sheep.sheets.CellLocation;

import java.util.List;

/**
 * Allows tetros pieces to be unrendered from their old position, and rendered at their new positions
 */
public interface Render {

    /**
     * Removes the tetros pieces from their old position
     * @param items List of tetros pieces to remove
     */
    void unrender(List<CellLocation> items);

    /**
     * Renders tetros pieces in their new updated positions
     * @param items List of tetros pieces to render
     */
    void render(List<CellLocation> items);
}
