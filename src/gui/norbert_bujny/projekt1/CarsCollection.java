package gui.norbert_bujny.projekt1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarsCollection {
    private Map<String, BaseCar> carsCollection;

    public CarsCollection() {
        this.carsCollection = new HashMap<>();
    }

    CarsCollection(ModuleConfig config) {
//        List<List<String>> configRows = config.getConfigRows();
//        this.stationsConnections = new HashMap<>();
//
//        for (List<String> configRow : configRows) {
//            Station newStation = new Station(configRow.get(0), configRow.get(1));
//            this.addStationToList(newStation, Utilities.parseCommaSeparatedStringToList(configRow.get(2)));
//        }
    }

    public void addCar(BaseCar car) {
        this.carsCollection.put(car.getID(), car);
    }

    public String getCarsList() {
        return this.carsCollection.values().stream().map(c -> c.toString()).collect(Collectors.joining("\n===\n"));
    }

    public BaseCar getCar(String ID) {
        return this.carsCollection.get(ID);
    }

    public void deleteCar(String ID) {
        this.carsCollection.remove(ID);
    }
}
