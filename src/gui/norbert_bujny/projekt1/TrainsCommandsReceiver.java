package gui.norbert_bujny.projekt1;

import java.util.List;

public class TrainsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;
    private TrainsCollection trainsCollection;
    private CarsCollection carsCollection;
    private TrainsDirector trainsDirector;

    public TrainsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
        this.trainsCollection = appReference.getTrainsCollection();
        this.carsCollection = appReference.getCarsCollection();
        this.trainsDirector = appReference.getTrainsDirector();
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

        this.trainsCollection.addItem(new Train(homeStation, targetStation, trainsDirector));
        System.out.println("Dodano pociąg");
    }

    public void attachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getItemWithPrompt("Podaj ID wagonu");

            train.attachCar(car);
            car.setIsAttachedTo(train);
            System.out.println("Przyczepiono wagon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void detachCar() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");
            BaseCar car = this.carsCollection.getItemWithPrompt("Podaj ID wagonu");

            train.detachCar(car);
            car.setIsAttachedTo(null);
            System.out.println("Odczepiono wagon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTrainsList() {
        System.out.println(this.trainsCollection.getItemsList());
    }

    public void deleteTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            List<BaseCar> cars = train.getCars();
            for (BaseCar car : cars) {
                car.setIsAttachedTo(null);
            }

            trainsCollection.deleteItem(train.getID());
            System.out.println("Usunięto pociąg");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
