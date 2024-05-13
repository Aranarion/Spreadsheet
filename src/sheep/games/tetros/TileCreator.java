package sheep.games.tetros;

/**
 * Interface for controlling the creation and instantiation of new tetros pieces and starting their drop.
 * This interface defines the method for dropping a new tetros piece onto the game board.
 */
public interface TileCreator {
    /**
     * Drops a new tetros piece onto the game board.
     *
     * @return {@code true} if the piece immediately collides with another piece, resulting in a game over; {@code false} otherwise.
     * @post Depending on the implementation, this method may drop a new tetros piece onto the game board. If the piece collides with another piece upon dropping, the method returns true, indicating that the game is over. Otherwise, it returns false.
     */
    boolean drop();
}
