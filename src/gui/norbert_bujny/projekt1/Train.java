package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Train implements IdRepresentedItem, Serializable {
    private String ID;

    private String name;
    private int maxCarsCount;
    private double maxWeight;
    private int maxElectricCarsCount;
    private Station homeStation;

    private Station targetStation;
    private TrainRideDirection rideDirection;
    private Station currentStation;
    private List<BaseCar> cars;
    private List<Station> currentPath;
    private TrainsDirector director;

    public Train(Station homeStation, Station targetStation) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.cars = new ArrayList<>();
        this.rideDirection = TrainRideDirection.GO_TO_TARGET;
        this.initializeTrain();
    }

    private void initializeTrain() {
        this.name = Utilities.handleUserRequiredInput("Nazwa: ");
        this.maxWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Maksymalna waga transportowanego ładunku: "));
        this.maxCarsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna liczba wagonów: "));
        this.maxElectricCarsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna liczba wagonów wymagających podłączenia do sieci elektrycznej: "));
    }

    public void runTrain() {
        try {
            if (this.rideDirection.equals(TrainRideDirection.GO_TO_TARGET)) {
                this.currentPath = this.director.requestPath(this, this.homeStation, this.targetStation);
            } else {
                this.currentPath = this.director.requestPath(this, this.targetStation, this.homeStation);
            }
        } catch (PathNotFoundException e) {
//            TODO TRY AGAIN
        }

    }

    public void attachCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException {
        if (this.canAddCar(car)) this.cars.add(car);
    }

    public void detachCar(BaseCar car) {
        this.cars.remove(car);
    }

    public List<BaseCar> getCars() {
        return this.cars;
    }

    private double sumCarsGrossWeight() {
        double sum = 0;
        for (BaseCar car : this.getCars()) {
            sum += car.getGrossWeight();
        }

        return sum;
    }

    private boolean canAddCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException {
        if (this.cars.size() >= this.maxCarsCount) throw new TooManyCarsException();
        if (car.getNeedsElectricity() && this.getElectricCarsCount() >= this.maxElectricCarsCount)
            throw new TooManyElectricCarsException();
        if (car.getGrossWeight() >= this.maxWeight - this.sumCarsGrossWeight()) throw new TooHeavyCarException();

        return true;
    }

    private int getElectricCarsCount() {
        return this.cars.stream()
                .filter(car -> car.getNeedsElectricity())
                .toList()
                .size();
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ",\nNazwa pociągu: " + this.name +
                ",\nStacja startowa: " + this.homeStation +
                ",\nStacja docelowa: " + this.targetStation +
                ",\nMaksymalna ilość wagonów: " + this.maxCarsCount +
                ",\nMaksymalna ilość wagonów wymagających podłączenia do sieci elektrycznej: " + this.maxElectricCarsCount +
                ",\nMaksymalna waga transportowanego ładunku: " + this.maxWeight +
                ",\nWagony: " + this.cars.stream().map(car -> car.toShortString() + "\n\t").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return maxCarsCount == train.maxCarsCount && Double.compare(train.maxWeight, maxWeight) == 0 && maxElectricCarsCount == train.maxElectricCarsCount && Objects.equals(ID, train.ID) && Objects.equals(name, train.name) && Objects.equals(homeStation, train.homeStation) && Objects.equals(targetStation, train.targetStation) && Objects.equals(currentStation, train.currentStation) && Objects.equals(cars, train.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, maxCarsCount, maxWeight, maxElectricCarsCount, homeStation, targetStation, currentStation, cars);
    }
}
