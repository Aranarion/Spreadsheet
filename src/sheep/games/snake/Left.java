package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Changes direction of snake to left
 */
public class Left implements Perform {
    private Snake snake;

    /**
     * Constructs the left class
     * @param snake current iteration of the snake
     */
    public Left(Snake snake) {
        this.snake = snake;
    }

    /**
     * Changes the direction of the snake to the left.
     *
     * @param row     ignored
     * @param column  ignored
     * @param prompt  ignored
     * @pre the {@code snake} must not be null
     * @post the direction of the snake is set to left
     */
    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("left");
    }
}
