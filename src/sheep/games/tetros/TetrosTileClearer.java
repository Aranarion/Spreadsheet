package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

/**
 * Clears rows of stacked pieces from the tetros board.
 * This class is responsible for clearing full rows of stacked tetros pieces from the board.
 */
public class TetrosTileClearer implements TileClearer {
    private final Tetros tetros;
    private final Sheet sheet;

    /**
     * Constructs the tetros tile clearer.
     *
     * @param tetros The current instance of tetros.
     * @param sheet  The current iteration of the spreadsheet.
     * @pre tetros != null && sheet != null
     * @post A new tetros tile clearer instance is created with the provided parameters.
     */
    public TetrosTileClearer(Tetros tetros, Sheet sheet) {
        this.tetros = tetros;
        this.sheet = sheet;
    }

    /**
     * Clears full rows from the tetros board.
     * If a row is fully occupied by tetros pieces, it removes the row and shifts all above rows down.
     *
     * @pre The tetros board must be initialized with tetros pieces placed on it.
     * @post Full rows of tetros pieces are cleared from the board, and above rows are shifted down if necessary.
     */
    public void clear() {
        for (int row = sheet.getRows() - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0; col < sheet.getColumns(); col++) {
                if (sheet.valueAt(row, col).getContent().isEmpty()) {
                    full = false;
                }
            }

            if (full) {
                for (int rowX = row; rowX > 0; rowX--) {
                    for (int col = 0; col < sheet.getColumns(); col++) {
                        try {
                            if (tetros.accessContents().contains(new CellLocation(rowX - 1, col))) {
                                continue;
                            }
                            sheet.update(new CellLocation(rowX, col),
                                    sheet.valueAt(new CellLocation(rowX - 1, col)));

                        } catch (TypeError e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                row = row + 1;
            }
        }
    }
}
