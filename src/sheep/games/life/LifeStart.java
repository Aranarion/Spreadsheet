package sheep.games.life;

import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Initializes the Game of Life simulation.
 */
public class LifeStart implements Perform {
    private final Sheet sheet;
    private final Life life;

    /**
     * Constructs the initialization feature for the Game of Life simulation.
     *
     * @param sheet the current spreadsheet
     * @param life  the current simulation
     */
    public LifeStart(Sheet sheet, Life life) {
        this.sheet = sheet;
        this.life = life;
    }

    /**
     * Initializes the Game of Life simulation.
     * @param row     ignored
     * @param column  ignored
     * @param prompt  ignored
     * @pre the {@code sheet} must not be null
     * @pre the {@code life} must not be null
     * @post the Game of Life simulation is started
     */
    @Override
    public void perform(int row, int column, Prompt prompt) {
        setUp();
        life.updateStarted(true);
    }

    /**
     * Sets up the initial state of the spreadsheet for the Game of Life simulation.
     */
    private void setUp() {
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                if (sheet.valueAt(i, j).getContent().equals("1")) {
                    sheet.update(i, j, "1");
                } else {
                    sheet.update(i, j, "");
                }
            }
        }
    }
}
