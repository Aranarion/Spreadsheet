package sheep.games.snake;

import sheep.sheets.Sheet;
import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Initialises the snake feature on the spreadsheet
 */
public class SnakeStart implements Perform {
    private final Snake snake;
    private final Sheet sheet;

    /**
     * Initialises the start of the snake feature
     * @param snake the current iteration of the snake
     * @param sheet the current iteration of the spreadsheet
     */
    public SnakeStart(Snake snake, Sheet sheet) {
        this.snake = snake;
        this.sheet = sheet;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        if (row == -2 || column == -2) {
            row = 0;
            column = 0;
        }
        snake.updateSnakeCoordinates(row + "," + column);
        setUp();
        snake.updateStarted(true);
    }

    private void setUp() {
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                if (sheet.valueAt(i, j).getContent().isEmpty()) {
                    String string = "";
                } else {
                    sheet.update(i, j, "2");
                }
            }
        }
        String[] initialCoordinates = snake.getSnakeCoordinates().getFirst().split(",");
        sheet.update(Integer.parseInt(initialCoordinates[0]),
                Integer.parseInt(initialCoordinates[1]), "1");
    }
}
