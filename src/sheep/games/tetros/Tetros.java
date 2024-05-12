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
 * Controls the function of the tetros feature
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
     * Constructs the tetros class
     * @param sheet the current iteration of the spreadsheet
     * @param randomTile implements randomtile which will be used to pick random tile for food
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
     * Allows the current list of cell locations of the tetros pieces to be accessed
     * @return list of cell locations of the tetros pieces
     */
    public List<CellLocation> accessContents() {
        return contents;
    }

    /**
     * Sets the locations of the tetros pieces
     * @param newContents List of new cell locations of all tetros pieces
     */
    public void newContents(List<CellLocation> newContents) {
        contents = newContents;
    }

    /**
     * Accesses current dropping tetros piece's falling type
     * @return integer which is formula of tetros piece's on spreadsheet
     */
    public int getFallingType() {
        return fallingType;
    }

    /**
     * Allows falling type to be changed
     * @param newFallingType new integer which is formula of new tetros piece's on spreadsheet
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
     * Initialises the start of tetros
     * @return perform the initialisation of tetros
     */
    public Perform getStart() {
        return new GameStart(this, tileCreator);
    }

    /**
     * accesses whether tetros has started or not
     * @return true if tetros has started
     */
    public boolean getStarted() {
        return started;
    }

    /**
     * When tetros is initialised, sets boolean of started as true
     */
    public void updateStarted() {
        started = true;
    }

    /**
     * When a keybind indicating a player shift tetros piece will instantiate that shift
     * @param direction the setting of the move, 1 is right and -1 is left
     * @return shift class
     */
    public Perform getMove(int direction) {
        return new Shift(direction, this, renderer, tileDropper, sheet);
    }

    /**
     * When a keybind indicating a player rotates tetros piece will instantiate that rotation
     * @param direction the setting of the move, 1 is right and -1 is left
     * @return rotate class
     */
    public Perform getRotate(int direction) {
        return new Rotate(direction, this, renderer, sheet);
    }
}
