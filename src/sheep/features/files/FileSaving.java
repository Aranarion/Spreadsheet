package sheep.features.files;

import sheep.features.Feature;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

/**
 * This class implements the file saving feature which saves the current spreadsheet as a file.
 */
public class FileSaving implements Feature, Tick, Perform {
    private final Sheet sheet;
    private boolean error = false;

    /**
     * Constructor for file saving feature
     * @param sheet the current spreadsheet
     */
    public FileSaving(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public void register(UI ui) {
        ui.onTick(this);
        ui.addFeature("save-file", "Save File", this);
    }

    @Override
    public boolean onTick(Prompt prompt) {
        if (error) {
            prompt.message("An error has been made");
            error = false;
        }
        return false;
    }

    /**
     * Saves the current spreadsheet to the specified file path.
     *
     * @param filePath the path to save the file
     * @throws IOException if an I/O error occurs while writing the file
     * @pre The specified file path must be valid and writable.
     * @post The current spreadsheet is saved as a file at the specified path. If an error occurs during the saving process, an IOException is thrown.
     */
    private void saveFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(sheet.getRows() + "|" + sheet.getColumns() + "\n");
            writer.write(sheet.encode());
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public void perform(int row, int column, Prompt prompt) {
        Optional<String> path = prompt.ask("File Path: ");
        String filePath;
        filePath = path.orElse("");
        try {
            saveFile(filePath);
        } catch (IOException e) {
            prompt.message("An error has occurred.");
        }
    }
}
