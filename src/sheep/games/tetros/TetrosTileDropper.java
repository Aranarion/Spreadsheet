package sheep.games.tetros;

import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the downward movement of the tile each tick
 */
public class TetrosTileDropper implements TileDropper {
    private final Tetros tetros;
    private final Render renderer;
    private final Sheet sheet;

    /**
     * Constructs the tile dropper
     * @param sheet the current instance of the sheet
     * @param tetros the current instance of the tetros game
     * @param renderer the chosen renderer
     */
    public TetrosTileDropper(Sheet sheet, Tetros tetros, Render renderer) {
        this.tetros = tetros;
        this.renderer = renderer;
        this.sheet = sheet;
    }

    /**
     * Lowers the entire tetros piece by one row until it cannot fall anymore
     * @return true if tetros piece has finished falling
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
     * Determines if current fall tick will stop the tetros piece
     * @param location a cell location of the tetros piece
     * @return true if tetros piece collides with another piece or bounds
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
