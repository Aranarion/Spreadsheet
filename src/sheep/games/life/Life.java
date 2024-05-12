package sheep.games.life;

import sheep.features.Feature;
import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;
import sheep.ui.Tick;
import sheep.ui.UI;

public class Life implements Feature, Tick {
    private final Sheet sheet;
    private boolean started = false;
    public Life (Sheet sheet) {
        this.sheet = sheet;
    }
    public void updateStarted(boolean newStarted) {
        started = newStarted;
    }
    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("gol-start", "GOL Start", new GOLStart(sheet, this));
        ui.addFeature("gol-end", "GOL End", new GOLEnd());
    }
    public int numberNeighbour(int row, int column, Sheet previousSheet) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i < 0 || i >= previousSheet.getRows() || j < 0 || j >= previousSheet.getColumns() || (i == row && j == column)) {
                    continue;
                }
                if (previousSheet.valueAt(i, j).getContent().equals("1")) {
                    count += 1;
                }
            }
        }
        return count;
    }
    public boolean onNextTurn(int row, int column, boolean on, Sheet previousSheet) {
        int numberNeighbours = numberNeighbour(row, column, previousSheet);
        if (on) {
            return (numberNeighbours) >= 2 && numberNeighbours <= 3;
        } else {
            return numberNeighbours == 3;
        }
    }
    public void updateSheet() {
        String[][] newSheet = new String[sheet.getRows()][sheet.getColumns()];
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                boolean currentlyOn;
                currentlyOn = sheet.valueAt(i, j).getContent().equals("1");
                boolean turn = onNextTurn(i, j, currentlyOn, sheet);
                if (turn) {
                    newSheet[i][j] = "1";
                } else {
                    newSheet[i][j] = "";
                }
            }
        }
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                sheet.update(i, j, newSheet[i][j]);
            }
        }
    }
    public class GOLEnd implements Perform {
        @Override
        public void perform(int row, int column, Prompt prompt) {
            started = false;
        }
    }

    @Override
    public boolean onTick(Prompt prompt) {
        if (!started) {
            return false;
        } else {
            updateSheet();
            return true;
        }
    }
}
