package sheep.games.tetros;

import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

public class TetrosTileDropper implements TileDropper {
    private final Tetros tetros;
    private final Render renderer;
    private final Sheet sheet;
    public TetrosTileDropper(Sheet sheet, Tetros tetros, Render renderer) {
        this.tetros = tetros;
        this.renderer = renderer;
        this.sheet = sheet;
    }
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
