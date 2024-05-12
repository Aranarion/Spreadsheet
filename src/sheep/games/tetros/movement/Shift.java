package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.games.tetros.TileDropper;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

public class Shift extends Movement {
    private Render renderer;
    private TileDropper tileDropper;

    public Shift(int direction, Tetros tetros, Render renderer, TileDropper tileDropper, Sheet sheet) {
        super(tetros, direction, sheet);
        this.renderer = renderer;
        this.tileDropper = tileDropper;
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
        if (!super.inBounds(newContents)) {
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
