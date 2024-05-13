package sheep.games.tetros;

import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the downward movement of the tile each tick.
 * This class handles the dropping of tetros tiles by lowering them by one row each tick until they cannot fall anymore.
 */
public class TetrosTileDropper implements TileDropper {
    private final Tetros tetros;
    private final Render renderer;
    private final Sheet sheet;

    /**
     * Constructs the tile dropper.
     *
     * @param sheet    The current instance of the sheet.
     * @param tetros   The current instance of the tetros game.
     * @param renderer The chosen renderer.
     * @pre sheet != null && tetros != null && renderer != null
     * @post A new TetrosTileDropper instance is created with the provided parameters.
     */
    public TetrosTileDropper(Sheet sheet, Tetros tetros, Render renderer) {
        this.tetros = tetros;
        this.renderer = renderer;
        this.sheet = sheet;
    }

    /**
     * Lowers the entire tetros piece by one row until it cannot fall anymore.
     *
     * @return True if the tetros piece has finished falling.
     * @pre None.
     * @post The tetros piece is lowered by one row each tick until it cannot fall anymore. The renderer is called to render the updated position of the tetros piece.
     */
    public boolean dropTile() {
        List<CellLocation> newContents = new ArrayList<>();
        for (CellLocation tile : tetros.accessContents()) {
            newContents.add(new CellLocation(tile.getRow() + 1, tile.getColumn()));
        }

        renderer.unrender(tetros.accessContents());
        for (CellLocation newLoc : newContents) {
            if (isStopper(newLoc)) {
                renderer.render(tetros.accessContents());
                return true;
            }
        }

        renderer.render(newContents);
        tetros.newContents(newContents);
        return false;
    }

    /**
     * Determines if the current fall tick will stop the tetros piece.
     *
     * @param location A cell location of the tetros piece.
     * @return True if the tetros piece collides with another piece or the bounds of the sheet.
     * @pre location != null
     * @post The method checks if the tetros piece at the given location collides with another piece or the bounds of the sheet.
     */
    private boolean isStopper(CellLocation location) {
        if (location.getRow() >= sheet.getRows()) {
            return true;
        }

        if (location.getColumn() >= sheet.getColumns()) {
            return true;
        }

        return !sheet.valueAt(location.getRow(), location.getColumn()).getContent().isEmpty();
    }
}
