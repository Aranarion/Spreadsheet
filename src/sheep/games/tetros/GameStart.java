package sheep.games.tetros;

import sheep.ui.Perform;
import sheep.ui.Prompt;

public class GameStart implements Perform {
    private Tetros tetros;
    private TileCreator tileCreator;
    public GameStart(Tetros tetros, TileCreator tileCreator) {
        this.tetros = tetros;
        this.tileCreator = tileCreator;
    }
    @Override
    public void perform(int row, int column, Prompt prompt) {
        tetros.Start();
        tileCreator.drop();
    }
}
