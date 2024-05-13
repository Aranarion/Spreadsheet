package sheep.games.tetros;

/**
 * Interface for clearing rows of stacked pieces from the tetros board.
 * This interface defines the method for controlling when and how parts of tetros pieces should be cleared, such as when a bottom row is completed.
 */
public interface TileClearer {

    /**
     * Clears rows of stacked pieces from the tetros board when certain conditions are met.
     *tetros
     * @post Depending on the implementation, this method may clear rows of stacked pieces from the tetros board when specific conditions, such as completing a bottom row, are met.
     */
    void clear();
}
