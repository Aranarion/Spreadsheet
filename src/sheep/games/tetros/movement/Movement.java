package sheep.games.tetros.movement;

import sheep.games.tetros.Tetros;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;

import java.util.List;

public abstract class Movement implements Perform {
    protected Tetros tetros;
    private final int direction;
    private final Sheet sheet;
    public Movement(Tetros tetros, int direction, Sheet sheet) {
        this.tetros = tetros;
        this.direction = direction;
        this.sheet = sheet;
    }

    public void perform(int row, int column, Prompt prompt) {
        if (!tetros.getStarted()) {
            return;
        }
        apply(direction);
    }
    abstract void apply(int direction);
    public boolean outOfBounds(List<CellLocation> locations) {
        for (CellLocation location : locations) {
            if (!sheet.contains(location)) {
                return true;
            }
        }
        return false;
    }
}
