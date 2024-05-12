package sheep.games.tetros.movement;

import sheep.games.tetros.Tetros;
import sheep.ui.Perform;
import sheep.ui.Prompt;

public abstract class Movement implements Perform {
    protected Tetros tetros;
    private int direction;
    public Movement(Tetros tetros, int direction) {
        this.tetros = tetros;
        this.direction = direction;
    }
    public void perform(int row, int column, Prompt prompt) {
        if (!tetros.getStarted()) {
            return;
        }
        apply(direction);
    }
    abstract void apply(int direction);
}
