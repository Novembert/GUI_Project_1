package gui.norbert_bujny.projekt1;

public class RailroadHazard extends Exception {
    public RailroadHazard(Train tooFastTrain) {
        super("Uwaga! Pociąg przekroczył prędkość 200km/h");
        System.out.println(tooFastTrain);
    }
}
