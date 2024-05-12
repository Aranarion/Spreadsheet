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

    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("left");
    }
}
