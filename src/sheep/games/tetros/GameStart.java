package sheep.games.tetros;

import sheep.ui.Perform;
import sheep.ui.Prompt;

public class GameStart implements Perform {
    private Tetros tetros;
    public GameStart(Tetros tetros) {
        this.tetros = tetros;
    }
    @Override
    public void perform(int row, int column, Prompt prompt) {
        tetros.Start();
        tetros.drop();
    }
}
