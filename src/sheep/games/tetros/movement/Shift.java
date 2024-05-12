package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.games.tetros.TileDropper;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a shift of the tetros piece
 */
public class Shift extends Movement {
    private Tetros tetros;
    private final Render renderer;
    private final TileDropper tileDropper;

    /**
     * Constructs the shift class
     * @param direction The direction of the shift, 1 is to the right, -1 is to the left
     * @param tetros The current iteration of tetros
     * @param renderer The chosen renderer for tetros
     * @param tileDropper The chosen tiledropper for tetros
     * @param sheet the current iteration of the spreadsheet
     */
    public Shift(int direction, Tetros tetros, Render renderer,
                 TileDropper tileDropper, Sheet sheet) {
        super(tetros, direction, sheet);
        this.renderer = renderer;
        this.tileDropper = tileDropper;
        this.tetros = tetros;
    }

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

    private void fullDrop() {
        while (!tileDropper.dropTile()) {
        }
    }
}
