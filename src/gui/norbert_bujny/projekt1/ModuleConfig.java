package gui.norbert_bujny.projekt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModuleConfig {
    private List<List<String>> configRows = new ArrayList<>();

    ModuleConfig(String fileName) {
        File file = new File(fileName);
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine()){
            String line = input.nextLine();
            this.configRows.add(Utilities.parseSeparatedStringToList(line, ";"));
        }
        input.close();
    }

    public List<List<String>> getConfigRows() {
        return configRows;
    }
}
