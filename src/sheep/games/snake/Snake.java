package sheep.games.snake;

import sheep.features.Feature;
import sheep.games.random.RandomCell;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the snake feature
 */
public class Snake implements Feature, Tick, OnChange {
    private final Sheet sheet;
    private List<String> snakeCoordinates = new ArrayList<>();
    private String direction = "down";
    private Boolean started = false;
    private final RandomCell randomCell;
    private boolean consumedFood = false;
    private boolean ended = false;

    /**
     * Constructs the snake class
     * @param sheet the current  sheet's ieration
     * @param randomCell the current random cell implementation
     */
    public Snake(Sheet sheet, RandomCell randomCell) {
        this.sheet = sheet;
        this.randomCell = randomCell;
    }

    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("snake", "Start Snake", new SnakeStart(this, sheet));
        ui.onKey("w", "Up", new Up(this));
        ui.onKey("s", "Down", new Down(this));
        ui.onKey("a", "Left", new Left(this));
        ui.onKey("d", "Right", new Right(this));
        ui.onChange(this);
    }

    @Override
    public boolean onTick(Prompt prompt) {
        boolean newFood = false;
        if (!started) {
            return false;
        } else {
            String nextTile = nextTile();
            if (outOfBounds(nextTile)) {
                started = false;
                prompt.message("Game Over!");
                snakeCoordinates = new ArrayList<>();
                return true;
            }
            if (!consumedFood) {
                sheet.update(tileRow(snakeCoordinates.getFirst()),
                        tileColumn(snakeCoordinates.getFirst()), "");
                snakeCoordinates.removeFirst();
            } else {
                consumedFood = false;
            }
            if (foodOnTile(nextTile)) {
                newFood = true;
                consumedFood = true;
            }
            if (snakeEatsItself(nextTile)) {
                started = false;
                prompt.message("Game Over!");
                snakeCoordinates = new ArrayList<>();
                return false;
            }
            sheet.update(tileRow(nextTile), tileColumn(nextTile), "1");
            snakeCoordinates.add(nextTile);
            if (newFood) {
                newFood();
            }
            return true;
        }
    }

    private void newFood() {
        int newFoodRow = randomCell.pick().getRow();
        int newFoodColumn = randomCell.pick().getColumn();
        if (!sheet.valueAt(newFoodRow, newFoodColumn).getContent().equals("1")) {
            sheet.update(newFoodRow, newFoodColumn, "2");
        }
    }

    private int tileRow(String coordinates) {
        return Integer.parseInt(coordinates.split(",")[0]);
    }

    private int tileColumn(String coordinates) {
        return Integer.parseInt(coordinates.split(",")[1]);
    }

    private String nextTile() {
        int currentRow = Integer.parseInt(snakeCoordinates.getLast().split(",")[0]);
        int currentColumn = Integer.parseInt(snakeCoordinates.getLast().split(",")[1]);
        return switch (direction) {
            case "up" -> (currentRow - 1) + "," + currentColumn;
            case "down" -> (currentRow + 1) + "," + currentColumn;
            case "right" -> currentRow + "," + (currentColumn + 1);
            case null, default -> currentRow + "," + (currentColumn - 1);
        };
    }

    private boolean foodOnTile(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        return sheet.valueAt(row, column).getContent().equals("2");
    }

    private boolean snakeEatsItself(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        return sheet.valueAt(row, column).getContent().equals("1");
    }

    private boolean outOfBounds(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        return row < 0 || column < 0 || row >= sheet.getRows() || column >= sheet.getColumns();
    }

    @Override
    public void change(Prompt prompt) {
        if (ended && started) {
            prompt.message("Game Over!");
            started = false;
            ended = false;
        }
    }

    /**
     * Updates the direction of the snake
     * @param newDirection "up", "left", "right", "down"
     */
    public void updateDirection(String newDirection) {
        direction = newDirection;
    }

    /**
     * Adds a new tile to the snake
     * @param coordinates of the new tile
     */
    public void updateSnakeCoordinates(String coordinates) {
        snakeCoordinates.add(coordinates);
    }

    /**
     * Accesses current snake's coordinates
     * @return returns current snake's coordinates
     */
    public List<String> getSnakeCoordinates() {
        return snakeCoordinates;
    }

    /**
     * Updates when the snake feature has been initialised
     * @param nowStarted true when the snake feature is started
     */
    public void updateStarted(boolean nowStarted) {
        started = nowStarted;
    }
}
