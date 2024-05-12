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

    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("up");
    }
}
