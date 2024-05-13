package sheep.games.tetros;

import sheep.features.Feature;
import sheep.games.random.RandomTile;
import sheep.games.tetros.movement.Shift;
import sheep.games.tetros.movement.Rotate;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.util.*;

/**
 * Controls the functionality of the tetros feature.
 * This class manages the tetros game mechanics such as piece movement, rotation, and game initialization.
 */
public class Tetros implements Tick, Feature {
    private final Sheet sheet;
    private boolean started = false;
    private final Render renderer;
    private final TileDropper tileDropper;
    private final TileClearer tileClearer;
    private final TileCreator tileCreator;
    private int fallingType = 1;
    private List<CellLocation> contents = new ArrayList<>();
    private final RandomTile randomTile;

    /**
     * Constructs the tetros game instance.
     *
     * @param sheet      The current iteration of the spreadsheet.
     * @param randomTile Implements RandomTile, used to pick a random tile for tetros.
     * @pre sheet != null && randomTile != null
     * @post A new tetros game instance is created with the provided parameters.
     */
    public Tetros(Sheet sheet, RandomTile randomTile) {
        this.sheet = sheet;
        this.randomTile = randomTile;
        this.renderer = new TetrosRender(sheet, this);
        this.tileDropper = new TetrosTileDropper(sheet, this, renderer);
        this.tileClearer = new TetrosTileClearer(this, sheet);
        this.tileCreator = new TetrosTileCreator(this, sheet, renderer, randomTile);
    }

    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("tetros", "Start Tetros", this.getStart());
        ui.onKey("a", "Move Left", this.getMove(-1));
        ui.onKey("d", "Move Right", this.getMove(1));
        ui.onKey("q", "Rotate Left", this.getRotate(-1));
        ui.onKey("e", "Rotate Right", this.getRotate(1));
        ui.onKey("s", "Drop", this.getMove(0));
    }

    /**
     * Accesses the current list of cell locations of the tetros pieces.
     *
     * @return The list of cell locations of the tetros pieces.
     */
    public List<CellLocation> accessContents() {
        return contents;
    }

    /**
     * Sets the locations of the tetros pieces.
     *
     * @param newContents The list of new cell locations of all tetros pieces.
     * @pre newContents != null
     * @post The tetros piece locations are updated with the provided list.
     */
    public void newContents(List<CellLocation> newContents) {
        contents = newContents;
    }

    /**
     * Accesses the current falling type of the dropping tetros piece.
     *
     * @return An integer representing the falling type of the tetros piece.
     */
    public int getFallingType() {
        return fallingType;
    }

    /**
     * Updates the falling type of the dropping tetros piece.
     *
     * @param newFallingType The new falling type to set.
     * @pre newFallingType > 0
     * @post The falling type of the tetros piece is updated.
     */
    public void updateFallingType(int newFallingType) {
        fallingType = newFallingType;
    }

    @Override
    public boolean onTick(Prompt prompt) {
        if (!started) {
            return false;
        }

        if (tileDropper.dropTile()) {
            if (tileCreator.drop()) {
                prompt.message("Game Over!");
                started = false;
            }
        }
        tileClearer.clear();
        return true;
    }

    /**
     * Initializes the start of tetros.
     *
     * @return A Perform object for initializing tetros.
     * @post tetros game is initialized and started.
     */
    public Perform getStart() {
        return new GameStart(this, tileCreator);
    }

    /**
     * Checks if tetros has started.
     *
     * @return True if tetros has started, false otherwise.
     */
    public boolean getStarted() {
        return started;
    }

    /**
     * Sets the started status of tetros to true.
     *
     * @post tetros game is set as started.
     */
    public void updateStarted() {
        started = true;
    }

    /**
     * Instantiates a shift action for tetros piece.
     *
     * @param direction The direction of the shift (1 for right, -1 for left).
     * @return A Perform object for shifting tetros piece.
     * @pre direction == 1 || direction == -1
     * @post A shift action is instantiated based on the provided direction.
     */
    public Perform getMove(int direction) {
        return new Shift(direction, this, renderer, tileDropper, sheet);
    }

    /**
     * Instantiates a rotation action for tetros piece.
     *
     * @param direction The direction of the rotation (1 for right, -1 for left).
     * @return A Perform object for rotating tetros piece.
     * @pre direction == 1 || direction == -1
     * @post A rotation action is instantiated based on the provided direction.
     */
    public Perform getRotate(int direction) {
        return new Rotate(direction, this, renderer, sheet);
    }
}
