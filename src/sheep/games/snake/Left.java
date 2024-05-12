package sheep.games.snake;

import sheep.ui.Perform;
import sheep.ui.Prompt;

public class Left implements Perform {
    private Snake snake;
    public Left(Snake snake) {
        this.snake = snake;
    }
    @Override
    public void perform(int row, int column, Prompt prompt) {
        snake.updateDirection("left");
    }
}
