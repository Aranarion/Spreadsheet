package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.expression.basic.Constant;
import sheep.expression.basic.Nothing;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.List;

/**
 * Implementation of rendering class for tetros
 */
public class TetrosRender implements Render {
    private final Sheet sheet;
    private final Tetros tetros;

    /**
     * Constructs the class
     * @param sheet current iteration of the spreadsheet
     * @param tetros current iteration of tetros
     */
    public TetrosRender(Sheet sheet, Tetros tetros) {
        this.sheet = sheet;
        this.tetros = tetros;
    }

    /**
     * Will remove the tetros pieces from their former position
     * @param items List of tetros pieces to remove
     */
    public void unrender(List<CellLocation> items) {
        for (CellLocation cell : items) {
            try {
                sheet.update(cell, new Nothing());
            } catch (TypeError e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Will render the tetros pieces to their new positions
     * @param items List of tetros pieces to render
     */
    public void render(List<CellLocation> items) {
        if (items.isEmpty()) {
            return;
        }
        for (CellLocation cell : items) {
            try {
                sheet.update(cell, new Constant(tetros.getFallingType()));
            } catch (TypeError e) {
                throw new RuntimeException(e);
            }
        }
    }
}
