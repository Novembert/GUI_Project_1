package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainCarsMap implements Serializable {
    private static TrainCarsMap instance;
    private Map<Train, List<BaseCar>> trainCarsMap = new HashMap<>();

    @Override
    public String toString() {
        return "TrainCarsMap{" +
                "trainCarsMap=" + trainCarsMap +
                '}';
    }

    private TrainCarsMap() {
    }

    public Map<Train, List<BaseCar>> getTrainCarsMap() {
        return trainCarsMap;
    }

    public void setMap(Map<Train, List<BaseCar>> trainCarsMap) {
        this.trainCarsMap = trainCarsMap;
    }

    public static TrainCarsMap getInstance() {
        if (instance == null) {
            instance = new TrainCarsMap();
        }
        return instance;
    }

    public void removeTrain(Train t) {
        this.detachAllCars(t);
        this.trainCarsMap.remove(t);
    }

    public void removeCar(BaseCar car) {
        Train trainWithCar = this.trainCarsMap
                .keySet()
                .stream()
                .filter(train -> trainCarsMap.get(train).contains(car))
                .collect(Collectors.toList()).get(0);
        this.detachCar(trainWithCar, car);
    }

    public void addTrain(Train t) {
        this.trainCarsMap.put(t, new ArrayList<>());
    }

    public void attachCar(Train t, BaseCar c) {
        this.trainCarsMap.get(t).add(c);
    }

    public void detachCar(Train t, BaseCar c) {
        this.trainCarsMap.get(t).remove(c);
    }

    public List<BaseCar> getCars(Train t) {
        try {
            List<BaseCar> carsList = this.trainCarsMap.get(t);
            return carsList;
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public List<BaseCar> getAllCars() {
        return this.trainCarsMap
                .values()
                .stream()
                .flatMap(c -> c.stream())
                .collect(Collectors.toList());
    }

    public String getLoadableCarsList(Train train) {
        return this.trainCarsMap
                .get(train)
                .stream()
                .filter(c -> c instanceof Loadable)
                .map(c -> c.toString())
                .collect(Collectors.joining("\n===\n"));
    }

    private void detachAllCars(Train t) {
        this.trainCarsMap.get(t).clear();
    }
}
