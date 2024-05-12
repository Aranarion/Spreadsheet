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
    private int fallingType = 1;
    private List<CellLocation> contents = new ArrayList<>();
    private final PieceGenerator[] pieceGenerators = {
            new PieceZero(),
            new PieceOne(),
            new PieceTwo(),
            new PieceThree(),
            new PieceFour(),
            new PieceFive(),
            new PieceSix()
    };
    public final RandomTile randomTile;

    public Tetros(Sheet sheet, RandomTile randomTile) {
        this.sheet = sheet;
        this.randomTile = randomTile;
        this.renderer = new Render(sheet, this);
        this.tileDropper = new TileDropper(sheet, this, renderer);
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

    public boolean drop() {
        contents = new ArrayList<>();
        newPiece();
        for (CellLocation location : contents) {
            if (!sheet.valueAt(location).render().equals("")) {
                return true;
            }
        }
        renderer.ununrender(contents);

        return false;
    }

    private void newPiece() {
        int value = randomTile.pick();
        pieceGenerators[value].generatePiece(contents);
        fallingType = pieceGenerators[value].generateFallingType();
    }
    @Override
    public boolean onTick(Prompt prompt) {
        if (!started) {
            return false;
        }

        if (tileDropper.dropTile()) {
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
        return new Move(direction, this, renderer, tileDropper);
    }
    public Perform getRotate(int direction) {
        return new Rotate(direction, this, renderer);
    }
}
