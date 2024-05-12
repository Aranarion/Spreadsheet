package sheep.games.tetros;

/**
 * Controls the creation and instantiation of new tetros piece and starts it's drop
 */
public interface TileCreator {
    /**
     * Drops a new tetros piece
     * @return true if the piece immediately collides with another piece and game over
     */
    boolean drop();
}
