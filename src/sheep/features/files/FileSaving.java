package sheep.features.files;

import sheep.features.Feature;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FileSaving implements Feature, Tick, Perform {
    private final Sheet sheet;
    private String filePath;
    private boolean error = false;
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
        if (error == true) {
            prompt.message("An error has been made");
            error = false;
        }
        return false;
    }
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
        if (path.isPresent()) {
            filePath = path.get();
        } else {
            filePath = "";
        }
        try {
            saveFile(filePath);
        } catch (IOException e)
        {prompt.message("An error has occurred.");}
    }
}
