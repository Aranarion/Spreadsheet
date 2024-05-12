package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.expression.basic.Constant;
import sheep.expression.basic.Nothing;
import sheep.features.Feature;
import sheep.games.random.RandomTile;
import sheep.games.tetros.piecegenerator.*;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.util.*;

public class Tetros implements Tick, Feature {
    private final Sheet sheet;
    private boolean started = false;
    private Render renderer;
    private TileDropper tileDropper;
    private TileClearer tileClearer;
    private TileCreator tileCreator;
    private int fallingType = 1;
    private List<CellLocation> contents = new ArrayList<>();
    public final RandomTile randomTile;

    public Tetros(Sheet sheet, RandomTile randomTile) {
        this.sheet = sheet;
        this.randomTile = randomTile;
        this.renderer = new Render(sheet, this);
        this.tileDropper = new TileDropper(sheet, this, renderer);
        this.tileClearer = new TileClearer(this, sheet);
        this.tileCreator = new TileCreator(this, sheet, renderer, randomTile);
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
    public boolean inBounds(List<CellLocation> locations) {
        for (CellLocation location : locations) {
            if (!sheet.contains(location)) {
                return false;
            }
        }
        return true;
    }
    public List<CellLocation> accessContents() {
        return contents;
    }
    public void newContents(List<CellLocation> newContents) {
        contents = newContents;
    }
    public int getFallingType() {
        return fallingType;
    }
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

    public Perform getStart() {
        return new GameStart(this, tileCreator);
    }
    public boolean hasStarted() {
        return started;
    }
    public void Start() {
        started = true;
    }

    public Perform getMove(int direction) {
        return new Move(direction, this, renderer, tileDropper);
    }
    public Perform getRotate(int direction) {
        return new Rotate(direction, this, renderer);
    }
}
