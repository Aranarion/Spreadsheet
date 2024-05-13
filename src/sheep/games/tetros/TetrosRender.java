package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.expression.basic.Constant;
import sheep.expression.basic.Nothing;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.List;

/**
 * Renders tetros pieces on a spreadsheet.
 * This class is responsible for rendering tetros pieces on a given spreadsheet based on their cell locations.
 */
public class TetrosRender implements Render {
    private final Sheet sheet;
    private final Tetros tetros;

    /**
     * Constructs the tetros renderer.
     *
     * @param sheet  The current iteration of the spreadsheet.
     * @param tetros The current iteration of tetros.
     * @pre sheet != null && tetros != null
     * @post A new tetros renderer instance is created with the provided parameters.
     */
    public TetrosRender(Sheet sheet, Tetros tetros) {
        this.sheet = sheet;
        this.tetros = tetros;
    }

    /**
     * Removes tetros pieces from their former position on the spreadsheet.
     *
     * @param items List of tetros pieces to remove.
     * @pre items != null
     * @post tetros pieces specified in the items list are removed from the spreadsheet.
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

    /**tetros
     * Renders tetros pieces to their new positions on the spreadsheet.
     *
     * @param items List of tetros pieces to render.
     * @pre items != null
     * @post tetros pieces specified in the items list are rendered on the spreadsheet.
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
