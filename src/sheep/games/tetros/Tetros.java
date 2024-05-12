package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.expression.basic.Constant;
import sheep.expression.basic.Nothing;
import sheep.features.Feature;
import sheep.games.random.RandomTile;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.util.*;

public class Tetros implements Tick, Feature {
    private final Sheet sheet;
    private boolean started = false;

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

    private int fallingType = 1;
    private List<CellLocation> contents = new ArrayList<>();

    private boolean isStopper(CellLocation location) {
        if (location.getRow() >= sheet.getRows()) {
            return true;
        }
        if (location.getColumn() >= sheet.getColumns()) {
            return true;
        }
        return !sheet.valueAt(location.getRow(), location.getColumn()).getContent().equals("");
    }
    public boolean inBounds(List<CellLocation> locations) {
        for (CellLocation location : locations) {
            if (!sheet.contains(location)) {
                return false;
            }
        }
        return true;
    }
    public boolean dropTile() {
        List<CellLocation> newContents = new ArrayList<>();
        for (CellLocation tile : contents) {
            newContents.add(new CellLocation(tile.getRow() + 1, tile.getColumn()));
        }
        unrender();
        for (CellLocation newLoc : newContents) {
            if (isStopper(newLoc)) {
                ununrender(contents);
                return true;
            }
        }
        ununrender(newContents);
        this.contents = newContents;
        return false;
    }

    public void unrender() {
        for (CellLocation cell : contents) {
            try {
                sheet.update(cell, new Nothing());
            } catch (TypeError e) {
                throw new RuntimeException(e);
            }
        }
    }
    public List<CellLocation> accessContents() {
        return contents;
    }
    public void newContents(List<CellLocation> newContents) {
        contents = newContents;
    }
    public void ununrender(List<CellLocation> items) {
        if (items.isEmpty()) {
            return;
        }
        for (CellLocation cell : items) {
            try {
                sheet.update(cell, new Constant(fallingType));
            } catch (TypeError e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final RandomTile randomTile;

    public Tetros(Sheet sheet, RandomTile randomTile) {
        this.sheet = sheet;
        this.randomTile = randomTile;
    }

    public boolean drop() {
        contents = new ArrayList<>();
        newPiece();
        for (CellLocation location : contents) {
            if (!sheet.valueAt(location).render().equals("")) {
                return true;
            }
        }
        ununrender(contents);

        return false;
    }

    private void newPiece() {
        int value = randomTile.pick();
        switch (value) {
            case 1 -> {
                contents.add(new CellLocation(0, 0));
                contents.add(new CellLocation(1, 0));
                contents.add(new CellLocation(2, 0));
                contents.add(new CellLocation(2, 1));
                fallingType = 7;
            }
            case 2 -> {
                contents.add(new CellLocation(0, 1));
                contents.add(new CellLocation(1, 1));
                contents.add(new CellLocation(2, 1));
                contents.add(new CellLocation(2, 0));
                fallingType = 5;
            }
            case 3 -> {
                contents.add(new CellLocation(0, 0));
                contents.add(new CellLocation(0, 1));
                contents.add(new CellLocation(0, 2));
                contents.add(new CellLocation(1, 1));
                fallingType = 8;
            }
            case 4 -> {
                contents.add(new CellLocation(0, 0));
                contents.add(new CellLocation(0, 1));
                contents.add(new CellLocation(1, 0));
                contents.add(new CellLocation(1, 1));
                fallingType = 3;
            }
            case 5 -> {
                contents.add(new CellLocation(0, 0));
                contents.add(new CellLocation(1, 0));
                contents.add(new CellLocation(2, 0));
                contents.add(new CellLocation(3, 0));
                fallingType = 6;
            }
            case 6 -> {
                contents.add(new CellLocation(0, 1));
                contents.add(new CellLocation(0, 2));
                contents.add(new CellLocation(1, 1));
                contents.add(new CellLocation(0, 1));
                fallingType = 2;
            }
            case 0 -> {
                contents.add(new CellLocation(0, 0));
                contents.add(new CellLocation(0, 1));
                contents.add(new CellLocation(1, 1));
                contents.add(new CellLocation(1, 2));
                fallingType = 4;
            }
        }
    }
    @Override
    public boolean onTick(Prompt prompt) {
        if (!started) {
            return false;
        }

        if (dropTile()) {
            if (drop()) {
                prompt.message("Game Over!");
                started = false;
            }
        }
        clear();
        return true;
    }

    private void clear() {
        for (int row = sheet.getRows() - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0 ; col < sheet.getColumns(); col++) {
                if (sheet.valueAt(row, col).getContent().equals("")) {
                    full = false;
                }
            }
            if (full) {
                for (int rowX = row; rowX > 0; rowX--) {
                    for (int col = 0 ; col < sheet.getColumns(); col++) {
                        try {
                            if (contents.contains(new CellLocation(rowX - 1, col))) {
                                continue;
                            }
                            sheet.update(new CellLocation(rowX, col), sheet.valueAt(new CellLocation(rowX - 1, col)));
                        } catch (TypeError e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                row = row + 1;
            }
        }
    }

    public Perform getStart() {
        return new GameStart(this);
    }
    public boolean hasStarted() {
        return started;
    }
    public void Start() {
        started = true;
    }

    public Perform getMove(int direction) {
        return new Move(direction, this);
    }
    public Perform getRotate(int direction) {
        return new Rotate(direction, this);
    }
}
