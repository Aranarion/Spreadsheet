package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Changes direction of snake to down
 */
public class Up implements Perform {
    private Snake snake;

    /**
     * Constructs the up class
     * @param snake current iteration of the snake
     */
    public Up(Snake snake) {
        this.snake = snake;
    }

    /**
     * Changes the direction of the snake to up.
     *
     * @param row     ignored
     * @param column  ignored
     * @param prompt  ignored
     * @pre the {@code snake} must not be null
     * @post the direction of the snake is set to up
     */
    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("up");
    }
}
