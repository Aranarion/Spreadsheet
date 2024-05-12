package sheep.games.tetros;

import sheep.sheets.CellLocation;
import sheep.ui.Perform;
import sheep.ui.Prompt;

import java.util.ArrayList;
import java.util.List;

public class Move implements Perform {
    private Tetros tetros;
    private final int direction;
    private Render renderer;

    public Move(int direction, Tetros tetros, Render renderer) {

        this.direction = direction;
        this.tetros = tetros;
        this.renderer = renderer;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        if (!tetros.hasStarted()) {
            return;
        }
        shift(direction);
    }
    private void shift(int x) {
        if (x == 0) {
            fullDrop();
            return;
        }
        List<CellLocation> newContents = new ArrayList<>();
        for (CellLocation tile : tetros.accessContents()) {
            newContents.add(new CellLocation(tile.getRow(), tile.getColumn() + x));
        }
        if (!tetros.inBounds(newContents)) {
            return;
        }
        renderer.unrender(tetros.accessContents());
        renderer.ununrender(newContents);
        tetros.newContents(newContents);
    }
    private void fullDrop() {
        while (!tetros.dropTile()) {
        }
    }
}
