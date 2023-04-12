package gui.norbert_bujny.projekt1;

import java.io.*;
import java.util.List;

public class TrainsReportGenerator implements Runnable {
    private TrainsCollection trainsCollection;
    private File outputFile = new File("output/report.txt");

    public TrainsReportGenerator(TrainsCollection trainsCollection) {
        this.trainsCollection = trainsCollection;

        new File("output").mkdir();

        try {
            FileWriter fw = new FileWriter(this.outputFile, true);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
