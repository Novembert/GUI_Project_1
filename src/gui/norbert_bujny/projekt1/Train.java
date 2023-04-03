package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class Train extends Thread implements IdRepresentedItem, Serializable {
    /**
     * train data
     */
    private String ID;
    private String name;
    private int maxCarsCount;
    private double maxWeight;
    private int maxElectricCarsCount;
    private List<BaseCar> cars;

    /**
     * stations data
     */
    private Station homeStation;

    private Station targetStation;
    private Station currentStation;

    /**
     * ride data
     */
    private TrainRideDirection rideDirection;
    private List<Connection> currentPath;
    private double speed;
    private double coveredCurrentPathDistance;
    private double coveredWholeTravelDistance;

    /**
     * other data
     */
    private TrainsDirector director;
    private final double KMhToKMsMultiplier = 0.0002777778;


    public Train(Station homeStation, Station targetStation, TrainsDirector director) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.cars = new ArrayList<>();
        this.rideDirection = TrainRideDirection.GO_TO_TARGET;
        this.speed = 100;
        this.director = director;
        this.initializeTrain();
        this.runTrain();
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

            this.coveredWholeTravelDistance = this.getSumPathDistance(this.currentPath);
            this.start();
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
        if (this.cars.size() >= this.maxCarsCount)
            throw new TooManyCarsException();
        if (car.getNeedsElectricity() && this.getElectricCarsCount() >= this.maxElectricCarsCount)
            throw new TooManyElectricCarsException();
        if (car.getGrossWeight() >= this.maxWeight - this.sumCarsGrossWeight())
            throw new TooHeavyCarException();

        return true;
    }

    private int getElectricCarsCount() {
        return this.cars.stream()
                .filter(car -> car.getNeedsElectricity())
                .toList()
                .size();
    }

    public void run() {
        Random random = new Random();

        Connection currentConnection = this.currentPath.get(0);
        Station targetStation = currentConnection.getTargetStation(this.currentStation);
        currentConnection.setIsUsed(true);
        this.coveredCurrentPathDistance = 0;

        while (this.coveredCurrentPathDistance < currentConnection.getDistance()) {
            System.out.println("Jadę...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                TODO obsluzyc to jakos
                throw new RuntimeException(e);
            }

            this.coveredCurrentPathDistance = this.coveredCurrentPathDistance + (this.KMhToKMsMultiplier * this.speed);
            this.coveredWholeTravelDistance = this.coveredWholeTravelDistance + this.coveredCurrentPathDistance;
            this.speed = random.nextBoolean() ? this.speed - this.speed * 0.03 : this.speed + this.speed * 0.03;
        }

        this.currentStation = targetStation;
        currentConnection.setIsUsed(false);
        this.speed = 100;
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

    private double getSumPathDistance(List<Connection> pathToCalculate) {
        double distance = 0;

        for (Connection connection : pathToCalculate) {
            distance += connection.getDistance();
        }

        return distance;
    }
}
