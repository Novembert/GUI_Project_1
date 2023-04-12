package gui.norbert_bujny.projekt1;

public class TrainsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;
    private TrainsCollection trainsCollection;
    private CarsCollection carsCollection;
    private TrainsDirector trainsDirector;
    private TrainCarsMap trainCarsMap;

    public TrainsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
        this.trainsCollection = appReference.getTrainsCollection();
        this.carsCollection = appReference.getCarsCollection();
        this.trainsDirector = appReference.getTrainsDirector();
        this.trainCarsMap = appReference.getTrainCarsMap();
    }

    public void addTrain() {
        Station homeStation = null;
        Station targetStation = null;

        int retry = 0;
        int MAX_RETRY = 3;
        do {
            try {
                homeStation = this.stationsMap.searchStationByCode(Utilities.handleUserRequiredInput("Podaj kod stacji startowej: "));
            } catch (StationNotFoundException e) {
                if (retry > 3) {
                    System.out.println("Nie udało się znaleźć stacji startowej.");
                    return;
                }
                retry++;
                System.out.println(e.getMessage());
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while (homeStation == null);

        retry = 0;
        do {
            try {
                targetStation = this.stationsMap.searchStationByCode(Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: "));
            } catch (StationNotFoundException e) {
                if (retry > 3) {
                    System.out.println("Nie udało się znaleźć stacji docelowej.");
                    return;
                }
                retry++;
                System.out.println(e.getMessage());
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while (targetStation == null);

        Train newTrain = new Train(homeStation, targetStation);
        this.trainsCollection.addItem(newTrain);
        this.trainCarsMap.addTrain(newTrain);
        System.out.println("Dodano pociąg");
    }

    public void attachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getNonAttachedCarWithPrompt("Podaj ID wagonu");

            train.attachCar(car);
            System.out.println("Przyczepiono wagon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void detachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getAttachedCarWithPrompt("Podaj ID wagonu");

            train.detachCar(car);
            System.out.println("Odczepiono wagon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTrainsList() {
//        System.out.println(this.trainCarsMap.getTrainCarsMap());
        System.out.println(this.trainsCollection.getItemsList());
    }

    public void printTrainReport() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            System.out.println(train.getReport());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            this.trainCarsMap.removeTrain(train);
            trainsCollection.deleteItem(train.getID());
            System.out.println("Usunięto pociąg");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            try {
                train.runTrain();
                System.out.println("Uruchomiono pociąg");
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runAllTrains() {
        int count = 0;
        int all = this.trainsCollection.getMap().size();
        for (String trainId : this.trainsCollection.getMap().keySet()) {
            try {
                Train t = this.trainsCollection.getItem(trainId);
                if (!t.isAlive()) {
                    t.runTrain();
                    count++;
                    System.out.println("Uruchomiono " + trainId);
                }
            } catch (Exception e) {
                System.out.println("Nie udało się uruchomić pociagu o ID: " + trainId);
            }
        }

        System.out.println("Uruchomiomo " + count + " " + Utilities.getCorrectSingularOrPluralForm(count, "pociąg", "pociągi", "pociągów"));
    }
}
