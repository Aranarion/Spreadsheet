package sheep.games.tetros;

import sheep.ui.Perform;
import sheep.ui.Prompt;

/**
 * Starts the tetros game feature by updating the game state and initiating tile dropping.
 */
public class GameStart implements Perform {
    private final Tetros tetros;
    private final TileCreator tileCreator;

    /**
     * Constructs the tetros game starter.
     *
     * @param tetros      The current instance of the tetros game.
     * @param tileCreator The chosen tile creator responsible for creating new tiles.
     */
    public GameStart(Tetros tetros, TileCreator tileCreator) {
        this.tetros = tetros;
        this.tileCreator = tileCreator;
    }

    /**
     * Starts the tetros game by updating the game state and initiating tile dropping.
     *
     * @param row     The row index.
     * @param column  The column index.
     * @param prompt  The prompt object.
     * @pre The tetros instance and TileCreator instance must not be null.
     * @post The game state is updated to started.
     *       A new tile is dropped on the game board.
     */
    @Override
    public void perform(int row, int column, Prompt prompt) {
        tetros.updateStarted();
        tileCreator.drop();
    }
}
