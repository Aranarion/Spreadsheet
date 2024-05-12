package sheep.games.tetros;

/**
 * Controls the sequential dropping of tetros pieces
 */
public interface TileDropper {
    /**
     * Controls how a tetros piece drops rows
     * @return true if tetros piece has finished falling
     */
    boolean dropTile();
}
