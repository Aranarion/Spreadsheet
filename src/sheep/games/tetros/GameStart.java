package sheep.games.tetros;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Starts the tetros feature
 */
public class GameStart implements Perform {
    private final Tetros tetros;
    private final TileCreator tileCreator;

    /**
     * Constructs the tetros feature initialisor
     * @param tetros the current iteration of tetros
     * @param tileCreator the chosen tile creator
     */
    public GameStart(Tetros tetros, TileCreator tileCreator) {
        this.tetros = tetros;
        this.tileCreator = tileCreator;
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        tetros.updateStarted();
        tileCreator.drop();
    }
}
