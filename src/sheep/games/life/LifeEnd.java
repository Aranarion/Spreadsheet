package sheep.games.life;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Ends the Game of Life simulation.
 */
public class LifeEnd implements Perform {
    private final Life life;

    /**
     * Constructs the ending feature for the Game of Life simulation.
     *
     * @param life the current instance of the Game of Life simulation
     */
    public LifeEnd(Life life) {
        this.life = life;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        life.updateStarted(false);
    }
}
