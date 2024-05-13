package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.games.tetros.TileDropper;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a shift of the tetros piece.
 * The shift moves the tetros piece horizontally by a specified direction (1 for right, -1 for left).
 */
public class Shift extends Movement {
    private Tetros tetros;
    private final Render renderer;
    private final TileDropper tileDropper;

    /**
     * Constructs the shift class.
     *
     * @param direction   The direction of the shift: 1 for right, -1 for left.
     * @param tetros      The current iteration of tetros.
     * @param renderer    The chosen renderer for tetros.
     * @param tileDropper The chosen tile dropper for tetros.
     * @param sheet       The current iteration of the spreadsheet.
     */
    public Shift(int direction, Tetros tetros, Render renderer,
                 TileDropper tileDropper, Sheet sheet) {
        super(tetros, direction, sheet);
        this.renderer = renderer;
        this.tileDropper = tileDropper;
        this.tetros = tetros;
    }

    /**
     * Applies the shift movement to the tetros piece.
     * If direction is 0, performs a full drop of the piece.
     * Otherwise, shifts the piece horizontally by the specified direction.
     *
     * @param direction The direction of the shift: 1 for right, -1 for left.
     * @pre The direction should be within the range [-1, 1].
     * @post The tetros piece is shifted horizontally by the specified direction, if possible.
     */
    @Override
    void apply(int direction) {
        if (direction == 0) {
            fullDrop();
            return;
        }
        List<CellLocation> newContents = new ArrayList<>();
        for (CellLocation tile : tetros.accessContents()) {
            newContents.add(new CellLocation(tile.getRow(), tile.getColumn() + direction));
        }
        if (super.outOfBounds(newContents)) {
            return;
        }
        renderer.unrender(tetros.accessContents());
        renderer.render(newContents);
        tetros.newContents(newContents);
    }

    /**
     * Fully drops the tetros piece to the bottom of the sheet.
     * This method is called when the direction is 0.
     */
    private void fullDrop() {
        while (!tileDropper.dropTile()) {
        }
    }
}
