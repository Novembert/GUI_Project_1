package gui.norbert_bujny.projekt1;

import java.util.List;

public class TrainsDirector {
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

    /**
     * should be invoked only at the init of app
     */
    public void runAllTrains() {
        for (Train train : this.trainsCollection.getMap().values()) {
            train.runTrain();
        }
    }
}
