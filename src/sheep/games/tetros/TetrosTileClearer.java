package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

public class TetrosTileClearer implements TileClearer {
    private Tetros tetros;
    private Sheet sheet;
    public TetrosTileClearer(Tetros tetros, Sheet sheet){
        this.tetros = tetros;
        this.sheet = sheet;
    }
    public void clear() {
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
                            if (tetros.accessContents().contains(new CellLocation(rowX - 1, col))) {
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
}
