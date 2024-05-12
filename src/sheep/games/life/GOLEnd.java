package sheep.games.life;

import sheep.ui.Perform;
import sheep.ui.Prompt;

public class GOLEnd implements Perform {
    private final Life life;
    public GOLEnd(Life life) {
        this.life = life;
    }
    @Override
    public void perform(int row, int column, Prompt prompt) {
        life.updateStarted(false);
    }
}
