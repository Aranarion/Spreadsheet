package sheep.games.tetros.movement;

import sheep.games.tetros.Tetros;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;

import java.util.List;

/**
 * Abstract class for defining user-inputted movements for Tetros.
 * This class provides a framework for implementing various types of movements.
 */
public abstract class Movement implements Perform {
    private final Tetros tetros;
    private final int direction;
    private final Sheet sheet;

    /**
     * Initializes a movement instance.
     *
     * @param tetros    the current instance of Tetros.
     * @param direction the direction of the movement.
     * @param sheet     the current instance of the spreadsheet.
     */
    public Movement(Tetros tetros, int direction, Sheet sheet) {
        this.tetros = tetros;
        this.direction = direction;
        this.sheet = sheet;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        if (!tetros.getStarted()) {
            return;
        }
        apply(direction);
    }

    /**
     * Abstract method to implement the movement
     * @param direction direction of the movement
     * @pre The direction should be valid and within the range defined for the specific movement.
     */
    abstract void apply(int direction);

    /**
     * Determines if the movement will be within the bounds of the spreadsheet
     * @param locations locations of the tetros pieces
     * @return true if piece location after movement is out of bounds
     * @post Returns true if and only if the resulting piece location after movement is out of bounds.
     */
    public boolean outOfBounds(List<CellLocation> locations) {
        for (CellLocation location : locations) {
            if (!sheet.contains(location)) {
                return true;
            }
        }
        return false;
    }
}
