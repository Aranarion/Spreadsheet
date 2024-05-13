package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Represents the action of changing the direction of the snake downwards.
 */
public class Down implements Perform {
    private Snake snake;

    /**
     * Constructs a new Down action.
     *
     * @param snake the current instance of the snake
     */
    public Down(Snake snake) {
        this.snake = snake;
    }

    /**
     * Changes the direction of the snake downwards.
     *
     * @param row     ignored
     * @param column  ignored
     * @param prompt  ignored
     * @pre the {@code snake} must not be null
     * @post the direction of the snake is set to down
     */
    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("down");
    }
}
