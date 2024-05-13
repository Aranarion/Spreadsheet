package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Rotates a Tetros piece clockwise or counterclockwise.
 * The direction determines the rotation direction: 1 for clockwise, -1 for counterclockwise.
 */
public class Rotate extends Movement {
    private final Render renderer;
    private Tetros tetros;

    /**
     * Initializes a rotation movement instance.
     *
     * @param direction The direction of the rotation: 1 for clockwise, -1 for counterclockwise.
     * @param tetros    The current Tetros instance.
     * @param renderer  The renderer used for displaying Tetros pieces.
     * @param sheet     The current spreadsheet instance.
     */
    public Rotate(int direction, Tetros tetros, Render renderer, Sheet sheet) {
        super(tetros, direction, sheet);
        this.renderer = renderer;
        this.tetros = tetros;
    }

    /**
     * Rotates the Tetros piece according to the specified direction.
     * Calculates the new positions of the Tetros cells after rotation and updates the Tetros instance.
     * If the new positions are out of bounds, the rotation is not applied.
     *
     * @param direction The direction of the rotation: 1 for clockwise, -1 for counterclockwise.
     * @pre The direction should be either 1 or -1.
     * @post If the rotation is successful, the Tetros piece is rotated according to the specified direction.
     *       Otherwise, the Tetros piece remains unchanged.
     */
    @Override
    void apply(int direction) {
        int x = 0;
        int y = 0;

        for (CellLocation cellLocation : tetros.accessContents()) {
            x += cellLocation.getColumn();
            y += cellLocation.getRow();
        }

        x /= tetros.accessContents().size();
        y /= tetros.accessContents().size();
        List<CellLocation> newCells = new ArrayList<>();

        for (CellLocation location : tetros.accessContents()) {
            int lx = x + ((y - location.getRow()) * direction);
            int ly = y + ((x - location.getColumn()) * direction);
            CellLocation replacement = new CellLocation(ly, lx);
            newCells.add(replacement);
        }

        if (super.outOfBounds(newCells)) {
            return;
        }

        renderer.unrender(tetros.accessContents());
        tetros.newContents(newCells);
        renderer.render(newCells);
    }
}