package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

public class Up implements Perform {
    private Snake snake;
    public Up(Snake snake) {
        this.snake = snake;
    }
    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("up");
    }
}
