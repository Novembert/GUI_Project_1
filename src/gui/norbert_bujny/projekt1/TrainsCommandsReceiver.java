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

    public void initialiseAddTrain() {
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

        this.runAddTrain(homeStation, targetStation);
    }

    public void runAddTrain(Station homeStation, Station targetStation) {
        Train newTrain = new Train(homeStation, targetStation);
        this.addTrainToCollections(newTrain);
    }

    public void addTrainToCollections(Train newTrain) {
        this.trainsCollection.addItem(newTrain);
        this.trainCarsMap.addTrain(newTrain);
        System.out.println("Dodano pociąg. ID pociągu: " + newTrain.getID());
    }

    public void initialiseAttachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getNonAttachedCarWithPrompt("Podaj ID wagonu");

            this.runAttachCar(train, car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runAttachCar(Train t, BaseCar c) {
        try {
            t.attachCar(c);
            System.out.println("Przyczepiono wagon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialiseDetachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getAttachedCarWithPrompt("Podaj ID wagonu");

            this.runDetachCar(train, car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runDetachCar(Train t, BaseCar c) {
        t.detachCar(c);
        System.out.println("Odczepiono wagon");
    }

    public void printTrainsList() {
        System.out.println(this.trainsCollection.getItemsList());
    }

    public void initialisePrintTrainReport() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            this.runPrintTrainReport(train);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runPrintTrainReport(Train t) {
        System.out.println(t.getReport());
    }

    public void initialiseDeleteTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            this.runDeleteTrain(train);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runDeleteTrain(Train t) {
        this.trainCarsMap.removeTrain(t);
        trainsCollection.deleteItem(t.getID());
        System.out.println("Usunięto pociąg");
    }

    public void initialiseRunTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            this.runTrain(train);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runTrain(Train t) {
        try {
            t.runTravel();
            System.out.println("Uruchomiono pociąg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runAllTrains() {
        int count = 0;
        for (String trainId : this.trainsCollection.getMap().keySet()) {
            try {
                Train t = this.trainsCollection.getItem(trainId);
                t.runTravel();
                count++;
                System.out.println("Uruchomiono " + trainId);
            } catch (IllegalTrainRideStateException e) {
                System.out.println("Nie udało się uruchomić pociagu o ID: " + trainId + ".\n" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Nie udało się uruchomić pociagu o ID: " + trainId);
            }
        }

        System.out.println("Uruchomiomo " + count + " " + Utilities.getCorrectSingularOrPluralForm(count, "pociąg", "pociągi", "pociągów"));
    }

    public void initialiseLoadCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            Loadable car = this.carsCollection.getLoadableCarWithPrompt("Podaj ID wagonu", train);

            this.runLoadCar(car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runLoadCar(Loadable c) {
        Command action = c.initLoadCargo();
        action.execute();
    }

    public void initialiseUnloadCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            Loadable car = this.carsCollection.getLoadableCarWithPrompt("Podaj ID wagonu", train);

            this.runUnloadCar(car);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void runUnloadCar(Loadable c) {
        Command action = c.initUnloadCargo();
        action.execute();
    }
}
