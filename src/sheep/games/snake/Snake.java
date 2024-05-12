package sheep.games.snake;

import sheep.features.Feature;
import sheep.games.random.RandomCell;
import sheep.games.random.RandomFreeCell;
import sheep.sheets.CellLocation;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake implements Feature, Tick, OnChange {
    private Sheet sheet;
    private int length;
    private List<String> snakeCoordinates = new ArrayList<>();
    private String direction = "down";
    private Boolean started = false;
    private RandomCell randomCell;
    private boolean consumedFood = false;
    private boolean ended = false;
    public Snake(Sheet sheet, RandomCell randomCell) {
        this.sheet = sheet;
        this.randomCell = randomCell;
    }

    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("snake", "Start Snake", new SnakeStart());
        ui.onKey("w", "Up", new Up());
        ui.onKey("s", "Down", new Down());
        ui.onKey("a", "Left", new Left());
        ui.onKey("d", "Right", new Right());
        ui.onChange(this);
    }

    @Override
    public boolean onTick(Prompt prompt) {
        boolean newFood = false;
        if (started == false) {
            return false;
        } else {
            String nextTile = nextTile();
            if (outOfBounds(nextTile) == true) {
                started = false;
                prompt.message("Game Over!");
                snakeCoordinates = new ArrayList<>();
                return true;
            }
            if (consumedFood == false) {
                sheet.update(tileRow(snakeCoordinates.get(0)), tileColumn(snakeCoordinates.get(0)), "");
                snakeCoordinates.remove(0);
            } else {
                consumedFood = false;
            }
            if (foodOnTile(nextTile) == true) {
                newFood = true;
                consumedFood = true;
            }
            if (snakeEatsItself(nextTile) == true) {
                started = false;
                prompt.message("Game Over!");
                snakeCoordinates = new ArrayList<>();
                return false;
            }
            sheet.update(tileRow(nextTile), tileColumn(nextTile), "1");
            snakeCoordinates.add(nextTile);
            if (newFood == true) {
                newFood();
                newFood = false;
            }
            return true;
        }
    }
    public void newFood() {
        int newFoodRow = randomCell.pick().getRow();
        int newFoodColumn = randomCell.pick().getColumn();
        if (!sheet.valueAt(newFoodRow, newFoodColumn).getContent().equals("1")) {
            sheet.update(newFoodRow, newFoodColumn, "2");
        }
    }
    public int tileRow(String coordinates) {
        return Integer.parseInt(coordinates.split(",")[0]);
    }
    public int tileColumn(String coordinates) {
        return Integer.parseInt(coordinates.split(",")[1]);
    }
    public String nextTile() {
        int currentRow = Integer.parseInt(snakeCoordinates.get(snakeCoordinates.size() - 1).split(",")[0]);
        int currentColumn = Integer.parseInt(snakeCoordinates.get(snakeCoordinates.size() - 1).split(",")[1]);
        if (direction == "up") {
            return (currentRow - 1) + "," + currentColumn;
        } else if (direction == "down") {
            return (currentRow + 1) + "," + currentColumn;
        } else if (direction == "right") {
            return currentRow + "," + (currentColumn + 1);
        } else {
            return currentRow + "," + (currentColumn - 1);
        }
    }
    public boolean foodOnTile(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        if (sheet.valueAt(row, column).getContent().equals("2")) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean snakeEatsItself(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        if (sheet.valueAt(row, column).getContent().equals("1")) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean outOfBounds(String coordinates) {
        int row = Integer.parseInt(coordinates.split(",")[0]);
        int column = Integer.parseInt(coordinates.split(",")[1]);
        if (row < 0 || column < 0 || row >= sheet.getRows() || column >= sheet.getColumns()) {
            return true;
        } else {
            return false;
        }
    }

    public void setUp() {
        for (int i = 0; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                if (sheet.valueAt(i, j).getContent().isEmpty()) {
                    continue;
                } else {
                    sheet.update(i, j, "2");
                }
            }
        }
        String[] initialCoordinates = snakeCoordinates.get(0).split(",");
        sheet.update(Integer.parseInt(initialCoordinates[0]), Integer.parseInt(initialCoordinates[1]), "1");
    }

    @Override
    public void change(Prompt prompt) {
        if (ended == true && started == true) {
            prompt.message("Game Over!");
            started = false;
            ended = false;
        }
    }

    public class SnakeStart implements Perform {

        @Override
        public void perform(int row, int column, Prompt prompt) {
            if (row == -2 || column == -2) {
                row = 0;
                column = 0;
            }
            snakeCoordinates.add(row + "," + column);
            setUp();
            started = true;
        }
    }
    public class SnakeEnd implements Perform {
        @Override
        public void perform(int row, int column, Prompt prompt) {
            prompt.message("Game Over!");
            started = false;
        }
    }
    public class Up implements Perform {

        @Override
        public void perform(int row, int column, Prompt prompt) {
            direction = "up";
        }
    }
    public class Down implements Perform {
        @Override
        public void perform(int row, int column, Prompt prompt) {
            direction = "down";
        }
    }
    public class Left implements Perform {
        @Override
        public void perform(int row, int column, Prompt prompt) {
            direction = "left";
        }
    }
    public class Right implements Perform {
        @Override
        public void perform(int row, int column, Prompt prompt) {
            direction = "right";
        }
    }
}
