package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Train implements IdRepresentedItem, Serializable {
    /**
     * train data
     */
    private String ID;
    private String name;
    private int maxCarsCount;
    private double maxWeight;
    private int maxElectricCarsCount;

    /**
     * stations data
     */
    private Station homeStation;

    private Station targetStation;
    private Station currentStation;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * ride data
     */
    private List<Connection> currentPath;
    private Queue<Connection> currentPathStationsQueue;
    private TrainRideState trainRideState;

    private double speed;
    private double coveredCurrentPathDistance;
    private double coveredWholeTravelDistance;
    private double currentPathDistance;
    private double wholeTravelDistance;

    /**
     * other data
     */
    private final double KMhToKMsMultiplier = 0.0002777778;

    public Train(Station homeStation, Station targetStation, String name, double maxWeight, int maxCarsCount, int maxElectricCarsCount) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.speed = 100;
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxCarsCount = maxCarsCount;
        this.maxElectricCarsCount = maxElectricCarsCount;
        this.trainRideState = TrainRideState.CREATED;
    }

    public Train(Station homeStation, Station targetStation) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.speed = 100;
        this.trainRideState = TrainRideState.CREATED;
        this.initializeTrain();
    }

    private void initializeTrain() {
        this.name = Utilities.handleUserRequiredInput("Nazwa: ");
        this.maxWeight = Utilities.handleUserRequiredInputDouble("Maksymalna waga transportowanego ładunku: ");
        this.maxCarsCount = Utilities.handleUserRequiredInputInt("Maksymalna liczba wagonów: ");
        this.maxElectricCarsCount = Utilities.handleUserRequiredInputInt("Maksymalna liczba wagonów wymagających podłączenia do sieci elektrycznej: ");
    }

    public void runTravel() throws IllegalTrainRideStateException {
        if (this.trainRideState != TrainRideState.CREATED && this.trainRideState != TrainRideState.READY_TO_START_NEW_TRAVEL) {
            throw new IllegalTrainRideStateException("Aktualny stan pociągu nie pozwala na uruchomienie go.");
        }

        try {
            if (this.currentStation.equals(this.targetStation)) {
                this.currentPath = App.getInstance().getTrainsDirector().requestPath(this, this.targetStation, this.homeStation);
                this.currentPathStationsQueue = this.generateStationsQueue(TrainRideDirection.GO_TO_HOME);
            } else {
                this.currentPath = App.getInstance().getTrainsDirector().requestPath(this, this.homeStation, this.targetStation);
                this.currentPathStationsQueue = this.generateStationsQueue(TrainRideDirection.GO_TO_TARGET);
            }

            this.wholeTravelDistance = this.getSumPathDistance(this.currentPath);
            this.trainRideState = TrainRideState.READY_TO_GO;

            this.tryToRun();
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
    }

    public void attachCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException, AlreadyAddedException {
        if (this.canAddCar(car)) TrainCarsMap.getInstance().attachCar(this, car);
    }

    public void detachCar(BaseCar car) {
        TrainCarsMap.getInstance().detachCar(this, car);
    }

    public TrainRideState getTrainRideState() {
        return this.trainRideState;
    }

    public void setTrainRideState(TrainRideState state) {
        this.trainRideState = state;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public Station getHomeStation() {
        return homeStation;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getKMhToKMsMultiplier() {
        return this.KMhToKMsMultiplier;
    }

    public double getCoveredCurrentPathDistance() {
        return coveredCurrentPathDistance;
    }

    public double getCurrentPathDistance() {
        return currentPathDistance;
    }

    public double getCoveredWholeTravelDistance() {
        return coveredWholeTravelDistance;
    }

    public void setCoveredCurrentPathDistance(double distance) {
        this.coveredCurrentPathDistance = distance;
    }

    public void setCurrentPathDistance(double currentPathDistance) {
        this.currentPathDistance = currentPathDistance;
    }

    public void setCoveredWholeTravelDistance(double coveredWholeTravelDistance) {
        this.coveredWholeTravelDistance = coveredWholeTravelDistance;
    }

    public List<BaseCar> getCars() {
        try {
            List<BaseCar> carsList = TrainCarsMap.getInstance().getCars(this);
            return carsList;
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    private double sumCarsGrossWeight() {
        double sum = 0;
        for (BaseCar car : this.getCars()) {
            sum += car.getGrossWeight();
        }

        return sum;
    }

    private boolean canAddCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException, AlreadyAddedException {
        if (this.getCars().size() >= this.maxCarsCount)
            throw new TooManyCarsException();
        if (car.getNeedsElectricity() && this.getElectricCarsCount() >= this.maxElectricCarsCount)
            throw new TooManyElectricCarsException();
        if (car.getGrossWeight() >= this.maxWeight - this.sumCarsGrossWeight())
            throw new TooHeavyCarException();
        if (this.getCars().contains(car))
            throw new AlreadyAddedException();
        return true;
    }

    private int getElectricCarsCount() {
        return this.getCars().stream()
                .filter(car -> car.getNeedsElectricity())
                .toList()
                .size();
    }

    public void tryToRun() {
        synchronized (this) {
            switch (this.trainRideState) {
                case RUNNING:
                    System.out.println("Coś poszło nie tak. Pociąg: " + this.getID());
                    break;
                case READY_TO_GO:
                    Connection currentConnection = this.currentPathStationsQueue.poll();

                    if (currentConnection.getIsUsed()) {
                        currentConnection.jumpInQueue(this);
                        this.trainRideState = TrainRideState.WAITING_IN_QUEUE;
                    } else {
                        executor.submit(new TrainRideAction(this, currentConnection));
                    }
                    break;
                case ARRIVED_TO_STATION:
                    this.trainRideState = TrainRideState.WAITING_AT_STATION;

                    executor.submit(new TrainWaitAndSwitchStateAction(this, 200, TrainRideState.READY_TO_GO));
                    break;
                case ARRIVED_TO_FINAL_STATION:
                    this.trainRideState = TrainRideState.WAITING_AT_FINAL_STATION;
                    executor.submit(new TrainWaitAndSwitchStateAction(this, 3000, TrainRideState.READY_TO_START_NEW_TRAVEL));
                    break;
                case READY_TO_START_NEW_TRAVEL:
                    try {
                        this.runTravel();
                    } catch (IllegalTrainRideStateException e) {
                        System.out.println("Nie udało się uruchomić pociagu o ID: " + this.getID() + ".\n" + e.getMessage());
                        this.trainRideState = TrainRideState.STOPPED;
                    }
                default:
                    break;

            }
        }
    }

    private Queue<Connection> generateStationsQueue(TrainRideDirection direction) {
        List<Connection> connectionsList = new ArrayList<>(this.currentPath);

        if (direction.equals(TrainRideDirection.GO_TO_HOME)) {
            Collections.reverse(connectionsList);
        }

        return new LinkedList<>(connectionsList);
    }


    public String getReport() {
        return this.getTrainInfo() +
                ",\n" + this.getCarsInfo(true) +
                ",\n" + this.getTravelInfo();
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public String toString() {
        return this.getTrainInfo() +
                ",\n" + this.getCarsInfo(false);
    }

    private String getTrainInfo() {
        return "ID: " + this.ID +
                ",\nNazwa pociągu: " + this.name +
                ",\nStacja startowa: " + this.homeStation +
                ",\nStacja docelowa: " + this.targetStation +
                ",\nMaksymalna ilość wagonów: " + this.maxCarsCount +
                ",\nMaksymalna ilość wagonów wymagających podłączenia do sieci elektrycznej: " + this.maxElectricCarsCount +
                ",\nMaksymalna waga transportowanego ładunku: " + this.maxWeight;
    }

    private String getCarsInfo(boolean extended) {
        List<BaseCar> carsList = this.getCars();

        if (carsList == null || carsList.size() == 0) {
            return "Wagony: Brak wagonów";
        }

        return "Wagony: " + this.getCars()
                .stream()
                .sorted(new Comparator<BaseCar>() {
                    @Override
                    public int compare(BaseCar o1, BaseCar o2) {
                        return (int) Math.round(o1.getNetWeight() - o2.getNetWeight());
                    }
                })
                .map(car -> extended ? car.toString() : car.toShortString()).collect(Collectors.joining("\n\t\t"));
    }

    private String getTravelInfo() {
        String result = "Aktualny przejazd: " + this.currentPath;
        result += "\nStan trasy: " + this.trainRideState + "\n";
        if (this.trainRideState == TrainRideState.RUNNING) {
            result += "Aktualny odcinek: " + Arrays.asList(this.currentPathStationsQueue.peek());
            result += "\nAktualna prędkość: " + speed;
            result += "\nPostęp drogi do następnej stacji: " + Utilities.visualizeProgress(this.coveredCurrentPathDistance, this.currentPathDistance)
                    + ",\n" + "Postęp całej trasy: " + Utilities.visualizeProgress(this.coveredWholeTravelDistance, this.wholeTravelDistance);
        } else {
            result += "Pociąg stoi na stacji: " + this.currentStation.toString();
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return maxCarsCount == train.maxCarsCount && Double.compare(train.maxWeight, maxWeight) == 0 && maxElectricCarsCount == train.maxElectricCarsCount && Objects.equals(ID, train.ID) && Objects.equals(name, train.name) && Objects.equals(homeStation, train.homeStation) && Objects.equals(targetStation, train.targetStation) && Objects.equals(currentStation, train.currentStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, maxCarsCount, maxWeight, maxElectricCarsCount, homeStation, targetStation, currentStation);
    }

    private double getSumPathDistance(List<Connection> pathToCalculate) {
        double distance = 0;

        for (Connection connection : pathToCalculate) {
            distance += connection.getDistance();
        }

        return distance;
    }
}
