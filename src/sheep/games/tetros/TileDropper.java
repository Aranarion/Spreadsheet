package sheep.games.tetros;

/**
 * Interface for controlling the sequential dropping of tetros pieces.
 * This interface defines the method for controlling how a tetros piece drops rows.
 */
public interface TileDropper {
    /**
     * Controls the dropping of a tetros piece by moving it one row down.
     *
     * @return {@code true} if the tetros piece has finished falling (i.e., it cannot move down further); {@code false} otherwise.
     * @post Depending on the implementation, this method may move the tetros piece one row down. If the tetros piece reaches the bottom of the game board or collides with another piece, the method returns true, indicating that the piece has finished falling. Otherwise, it returns false.
     */
    boolean dropTile();
}
