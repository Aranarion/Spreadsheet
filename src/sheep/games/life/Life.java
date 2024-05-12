package sheep.games.life;

import sheep.features.Feature;
import sheep.sheets.Sheet;
import sheep.ui.Prompt;
import sheep.ui.Tick;
import sheep.ui.UI;

/**
 * This class governs the behaviour of the game of life simulation
 */
public class Life implements Feature, Tick {
    private final Sheet sheet;
    private boolean started = false;

    /**
     * This is the constructor for the game of life
     * @param sheet the current iteration of the spreadsheet
     */
    public Life(Sheet sheet) {
        this.sheet = sheet;
    }

    /**
     * This method is used to update if the game of life simulation is ongoing
     * @param newStarted whether or not the simulation has started
     */
    public void updateStarted(boolean newStarted) {
        started = newStarted;
    }

    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("gol-start", "GOL Start", new LifeStart(sheet, this));
        ui.addFeature("gol-end", "GOL End", new LifeEnd(this));
    }

    /**
     * Calculates the number of on neighbours a cell has
     * @param row row of the cell
     * @param column column of the cell
     * @param previousSheet pre-tick iteration of the spreadsheet
     * @return number of neighbours that are on
     */
    public int numberNeighbour(int row, int column, Sheet previousSheet) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i < 0 || i >= previousSheet.getRows() || j < 0
                        || j >= previousSheet.getColumns() || (i == row && j == column)) {
                    continue;
                }
                if (previousSheet.valueAt(i, j).getContent().equals("1")) {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * Determines whether a cell is switched on, after the next tick
     * @param row of the cell
     * @param column of the cell
     * @param on whether the cell is currently on or not
     * @param previousSheet state of the spreadsheet before the next tick
     * @return true if cell will turn on after next tick
     */
    public boolean onNextTurn(int row, int column, boolean on, Sheet previousSheet) {
        int numberNeighbours = numberNeighbour(row, column, previousSheet);
        if (on) {
            return (numberNeighbours) >= 2 && numberNeighbours <= 3;
        } else {
            return numberNeighbours == 3;
        }
    }

    /**
     * At each tick determines the next iteration of the spreadsheet
     */
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
