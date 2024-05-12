package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Changes direction of snake to right
 */
public class Right implements Perform {
    private Snake snake;

    /**
     * Constructs the right class
     * @param snake current iteration of the snake
     */
    public Right(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("right");
    }
}
