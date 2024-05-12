package sheep.games.life;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * This class ends the Game of Life simulation
 */
public class LifeEnd implements Perform {
    private final Life life;

    /**
     * Constructor of the ending feature to the game of life simulation
     * @param life current game of life simulation
     */
    public LifeEnd(Life life) {
        this.life = life;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        life.updateStarted(false);
    }
}
