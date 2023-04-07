package gui.norbert_bujny.projekt1;

import java.io.*;
import java.util.List;

public class TrainsReportGenerator implements Runnable {
    private TrainsCollection trainsCollection;
    private File outputFile = new File("output/report.txt");

    public TrainsReportGenerator(TrainsCollection trainsCollection) {
        this.trainsCollection = trainsCollection;
    }

    public void run() {
        List<Train> trains = trainsCollection.getMap().values().stream().toList();
        String result = "";

        for (Train train : trains) {
//            result += train.getReport() + "\n";
        }

        try {
            FileWriter fw = new FileWriter(this.outputFile);
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
