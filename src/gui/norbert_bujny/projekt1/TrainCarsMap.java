package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainCarsMap implements Serializable {
    private static TrainCarsMap instance;

    @Override
    public String toString() {
        return "TrainCarsMap{" +
                "trainCarsMap=" + trainCarsMap +
                '}';
    }

    private Map<Train, List<BaseCar>> trainCarsMap = new HashMap<>();

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
        return this.trainCarsMap.get(t);
    }

    public List<BaseCar> getAllCars() {
        return this.trainCarsMap
                .values()
                .stream()
                .flatMap(c -> c.stream())
                .collect(Collectors.toList());
    }

    private void detachAllCars(Train t) {
        this.trainCarsMap.get(t).clear();
    }
}
