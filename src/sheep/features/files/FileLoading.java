package sheep.features.files;

import sheep.features.Feature;
import sheep.sheets.Sheet;
import sheep.ui.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class FileLoading implements Feature, Perform, OnChange {
    private final Sheet sheet;
    private String filePath;
    private boolean error = false;
    public FileLoading (Sheet sheet) {
        this.sheet = sheet;
    }
    @Override
    public void register(UI ui) {
            ui.addFeature("load-file", "Load File", this);
    }

    public boolean onChange(Prompt prompt) {
        prompt.message("An error has been made");
        error = false;
        return false;
    }

    @Override
    public void change(Prompt prompt) {}
    private void loadFile(String filePath) throws IOException {
        sheet.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rows = 0;
            int columns = 0;
            if ((line = reader.readLine()) != null) {
                String[] size = line.split("\\|");
                rows = Integer.parseInt(size[0]);
                columns = Integer.parseInt(size[1]);
                sheet.updateDimensions(rows, columns);
            }
            for (int j = 0; j < rows; j++) {
                line = reader.readLine();
                line = line.replaceAll("\\|", " , ");
                if (line != null) {
                    String[] expressions = line.split(",");
                    for (int i = 0; i < columns; i++) {
                        sheet.update(j, i, expressions[i].trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
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
            loadFile(filePath);
        } catch (FileNotFoundException e) {
            prompt.message("An error has occurred.");
        }
        catch (IOException e) {
            prompt.message("An error has occurred.");
        }
    }
}
