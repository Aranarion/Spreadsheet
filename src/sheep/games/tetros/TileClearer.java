package sheep.games.tetros;

/**
 * Interface of the tile clearer
 */
public interface TileClearer {

    /**
     * Controls when and how parts of tetros pieces should be cleared(ie when bottom row is completed)
     */
    void clear();
}
