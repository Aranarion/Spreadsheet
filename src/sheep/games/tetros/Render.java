package sheep.games.tetros;

import sheep.expression.TypeError;
import sheep.expression.basic.Constant;
import sheep.expression.basic.Nothing;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;

import java.util.List;

public class Render {
    private Sheet sheet;
    private Tetros tetros;
    public Render(Sheet sheet, Tetros tetros) {
        this.sheet = sheet;
        this.tetros = tetros;
    }
    public void unrender(List<CellLocation> items) {
        for (CellLocation cell : items) {
            try {
                sheet.update(cell, new Nothing());
            } catch (TypeError e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void ununrender(List<CellLocation> items) {
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
