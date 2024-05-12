package sheep.games.tetros;

import sheep.sheets.CellLocation;

import java.util.List;

public interface Render {
    void unrender(List<CellLocation> items);
    void render(List<CellLocation> items);
}
