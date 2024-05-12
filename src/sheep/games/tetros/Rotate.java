package sheep.games.tetros;

import sheep.sheets.CellLocation;
import sheep.ui.Perform;
import sheep.ui.Prompt;

import java.util.ArrayList;
import java.util.List;

public class Rotate implements Perform {
    private final int direction;
    private Tetros tetros;

    public Rotate(int direction, Tetros tetros) {
        this.direction = direction;
        this.tetros = tetros;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        if (!tetros.hasStarted()) {
            return;
        }
        flip(direction);
    }
    private void flip(int direction) {
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
        tetros.unrender();
        tetros.newContents(newCells);
        tetros.ununrender(newCells);
    }
}