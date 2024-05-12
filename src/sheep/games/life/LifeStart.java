package sheep.games.life;

import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * This class initialises the Game of Life simulation
 */
public class LifeStart implements Perform {
    private final Sheet sheet;
    private final Life life;

    /**
     * Constructor for the initialisation feature of the game of life simulation
     * @param sheet the current spreadsheet
     * @param life the current simulation
     */
    public LifeStart(Sheet sheet, Life life) {
        this.sheet = sheet;
        this.life = life;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        setUp();
        life.updateStarted(true);
    }

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
