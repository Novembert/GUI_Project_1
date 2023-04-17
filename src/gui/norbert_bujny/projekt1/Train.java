package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Train extends Thread implements IdRepresentedItem, Serializable {
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

    /**
     * ride data
     */
    private List<Connection> currentPath;
    private Queue<Connection> currentPathStationsQueue;

    private double speed;
    private double coveredCurrentPathDistance;
    private double coveredWholeTravelDistance;
    private double currentPathDistance;
    private double wholeTravelDistance;

    /**
     * other data
     */
    private final double KMhToKMsMultiplier = 0.0002777778;
    private Queue<Command> actionsQueue;

    public Train(Station homeStation, Station targetStation, String name, double maxWeight, int maxCarsCount, int maxElectricCarsCount) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.speed = 100;
        this.actionsQueue = new LinkedList<>();
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxCarsCount = maxCarsCount;
        this.maxElectricCarsCount = maxElectricCarsCount;
    }

    public Train(Station homeStation, Station targetStation) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.speed = 100;
        this.actionsQueue = new LinkedList<>();
        this.initializeTrain();
    }

    private void initializeTrain() {
        this.name = Utilities.handleUserRequiredInput("Nazwa: ");
        this.maxWeight = Utilities.handleUserRequiredInputDouble("Maksymalna waga transportowanego ładunku: ");
        this.maxCarsCount = Utilities.handleUserRequiredInputInt("Maksymalna liczba wagonów: ");
        this.maxElectricCarsCount = Utilities.handleUserRequiredInputInt("Maksymalna liczba wagonów wymagających podłączenia do sieci elektrycznej: ");
    }

    public void runTrain() {
        try {
            if (this.currentStation.equals(this.targetStation)) {
                this.currentPath = App.getInstance().getTrainsDirector().requestPath(this, this.targetStation, this.homeStation);
                this.currentPathStationsQueue = this.generateStationsQueue(TrainRideDirection.GO_TO_HOME);
            } else {
                this.currentPath = App.getInstance().getTrainsDirector().requestPath(this, this.homeStation, this.targetStation);
                this.currentPathStationsQueue = this.generateStationsQueue(TrainRideDirection.GO_TO_TARGET);
            }

            this.wholeTravelDistance = this.getSumPathDistance(this.currentPath);

            this.start();
        } catch (PathNotFoundException e) {
            System.out.println(e);
        }
    }

    public void attachCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException {
        if (this.canAddCar(car)) TrainCarsMap.getInstance().attachCar(this, car);
    }

    public void detachCar(BaseCar car) {
        TrainCarsMap.getInstance().detachCar(this, car);
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

    private boolean canAddCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException, TooHeavyCarException {
        if (this.getCars().size() >= this.maxCarsCount)
            throw new TooManyCarsException();
        if (car.getNeedsElectricity() && this.getElectricCarsCount() >= this.maxElectricCarsCount)
            throw new TooManyElectricCarsException();
        if (car.getGrossWeight() >= this.maxWeight - this.sumCarsGrossWeight())
            throw new TooHeavyCarException();
        return true;
    }

    private int getElectricCarsCount() {
        return this.getCars().stream()
                .filter(car -> car.getNeedsElectricity())
                .toList()
                .size();
    }

    public void run() {
        Connection currentConnection = this.currentPathStationsQueue.poll();

        synchronized (this) {
            if (currentConnection.getIsUsed()) {
                currentConnection.getInQueue(this);
            } else {
                this.rideToStation(currentConnection);
            }
        }
    }

    public void addAction(Command newActionCommand) {
        this.actionsQueue.add(newActionCommand);
    }

    private void rideToStation(Connection usedConnection) {
        Random random = new Random();

        Station targetStation = usedConnection.getTargetStation(this.currentStation);
        usedConnection.setIsUsed(true);
        this.coveredCurrentPathDistance = 0;
        this.currentPathDistance = usedConnection.getDistance();

        while (this.coveredCurrentPathDistance < this.currentPathDistance) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                TODO obsluzyc to jakos
                throw new RuntimeException(e);
            }

            double diff = this.KMhToKMsMultiplier * this.speed;
            this.coveredCurrentPathDistance = this.coveredCurrentPathDistance + diff;
            this.coveredWholeTravelDistance = this.coveredWholeTravelDistance + diff;

            this.speed = random.nextBoolean() ? this.speed - this.speed * 0.03 : this.speed + this.speed * 0.03;
        }

        this.currentStation = targetStation;
        usedConnection.setIsUsed(false);
        this.speed = 100;

        try {
            this.executeQueuedActions();
            if (targetStation.equals(this.targetStation) || targetStation.equals(this.homeStation)) {
                Thread.sleep(30000);
                this.runTrain();
            } else {
                Thread.sleep(200);
                this.start();
            }
        } catch (InterruptedException e) {
//                TODO obsluzyc to jakos
            throw new RuntimeException(e);
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

    private void executeQueuedActions() {
        while (!this.actionsQueue.isEmpty()) {
            Command currentCommand = this.actionsQueue.poll();
            currentCommand.execute();
        }
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
        result += "\nStan trasy: \n";
        if (this.isAlive()) {
            result += "Postęp drogi do następnej stacji: " + Utilities.visualizeProgress(this.coveredCurrentPathDistance, this.currentPathDistance)
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
