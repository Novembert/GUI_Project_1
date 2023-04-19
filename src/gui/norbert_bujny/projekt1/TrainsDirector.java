package gui.norbert_bujny.projekt1;

import java.util.List;

public class TrainsDirector implements Runnable {
    private StationsGraph stationsMap;
    private TrainsCollection trainsCollection;

    public TrainsDirector(StationsGraph map, TrainsCollection trains) {
        this.stationsMap = map;
        this.trainsCollection = trains;
    }

    public List<Connection> requestPath(Train train, Station source, Station destination) throws PathNotFoundException {
        try {
            return this.stationsMap.findPath(source.getCode(), destination.getCode());
        } catch (Exception e) {
            System.out.println("Uwaga! Pociąg " + train.getID() + " nie może znaleźć trasy z " + source.getCode() + " do " + destination.getCode());
            throw new PathNotFoundException();
        }
    }

    public void run() {
        try {
            if (trainsCollection.getMap().values().isEmpty()) return;

            for (Connection connection : stationsMap.getAllStationsConnections()) {
                connection.startTrainFromQueue();
            }

            for (Train t : trainsCollection.getMap().values()) {
                if (t.getTrainRideState() == TrainRideState.READY_TO_GO ||
                        t.getTrainRideState() == TrainRideState.DIRECTED_TO_GO ||
                        t.getTrainRideState() == TrainRideState.READY_TO_START_NEW_TRAVEL ||
                        t.getTrainRideState() == TrainRideState.ARRIVED_TO_STATION ||
                        t.getTrainRideState() == TrainRideState.ARRIVED_TO_FINAL_STATION) {
                    t.tryToRun();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
