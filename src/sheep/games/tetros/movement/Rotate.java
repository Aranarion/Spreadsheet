package sheep.games.tetros.movement;

import sheep.games.tetros.Render;
import sheep.games.tetros.Tetros;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Rotates the tetros piece
 */
public class Rotate extends Movement {
    private final Render renderer;
    private Tetros tetros;

    /**
     * Initialises the rotation class
     * @param direction The direction of the rotation, 1 is to the right, -1 is to the left
     * @param tetros The current implementation of tetros
     * @param renderer The chosen tetros renderer
     * @param sheet The current iteration of the spreadsheet
     */
    public Rotate(int direction, Tetros tetros, Render renderer, Sheet sheet) {
        super(tetros, direction, sheet);
        this.renderer = renderer;
        this.tetros = tetros;
    }

    @Override
    void apply(int direction) {
        int x = 0;
        int y = 0;
        for (CellLocation cellLocation : tetros.accessContents()) {
            x += cellLocation.getColumn();
            y += cellLocation.getRow();
        }
        x /= tetros.accessContents().size();
        y /= tetros.accessContents().size();
        List<CellLocation> newCells = new ArrayList<>();
        for (CellLocation location : tetros.accessContents()) {
            int lx = x + ((y - location.getRow()) * direction);
            int ly = y + ((x - location.getColumn()) * direction);
            CellLocation replacement = new CellLocation(ly, lx);
            newCells.add(replacement);
        }
        if (super.outOfBounds(newCells)) {
            return;
        }
        renderer.unrender(tetros.accessContents());
        tetros.newContents(newCells);
        renderer.render(newCells);
    }
}