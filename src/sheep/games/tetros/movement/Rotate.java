package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.sheets.CellLocation;
import sheep.ui.Perform;
import sheep.ui.Prompt;

import java.util.ArrayList;
import java.util.List;

public class Rotate extends Movement {
    private Render renderer;

    public Rotate(int direction, Tetros tetros, Render renderer) {
        super(tetros, direction);
        this.renderer = renderer;
    }
    @Override
    void apply(int direction) {
        int x = 0;
        int y = 0;
        for (CellLocation cellLocation : tetros.accessContents()) {
            x += cellLocation.getColumn();
            y += cellLocation.getRow();
        }
        x /= tetros.accessContents().size(); y /= tetros.accessContents().size();
        List<CellLocation> newCells = new ArrayList<>();
        for (CellLocation location : tetros.accessContents()) {
            int lx = x + ((y -location.getRow())*direction);
            int ly = y + ((x -location.getColumn())*direction);
            CellLocation replacement = new CellLocation(ly, lx);
            newCells.add(replacement);
        }
        if (!tetros.inBounds(newCells)) {
            return;
        }
        renderer.unrender(tetros.accessContents());
        tetros.newContents(newCells);
        renderer.ununrender(newCells);
    }
}