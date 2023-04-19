package gui.norbert_bujny.projekt1;

import java.io.*;
import java.util.List;

public class TrainsReportGenerator implements Runnable {
    private TrainsCollection trainsCollection;
    private File outputFile = new File("output/AppState.txt");

    public TrainsReportGenerator(TrainsCollection trainsCollection) {
        this.trainsCollection = trainsCollection;
        File outputDirectory = new File("output");
        this.deleteDir(outputDirectory);

        outputDirectory.mkdir();

        try {
            FileWriter fw = new FileWriter(this.outputFile, true);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    public void run() {
        if (trainsCollection.getMap().values().isEmpty()) return;

        List<Train> trains = trainsCollection.getMap().values().stream().toList();
        String result = "=================\n\n";

        for (Train train : trains) {
            result += train.getReport() + "\n\n";
        }

        result += "=================\n\n\n";

        try {
            FileWriter fw = new FileWriter(this.outputFile, true);
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
