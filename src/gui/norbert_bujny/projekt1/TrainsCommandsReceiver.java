package gui.norbert_bujny.projekt1;

import java.util.List;

public class TrainsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;
    private TrainsCollection trainsCollection;
    private CarsCollection carsCollection;

    public TrainsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
        this.trainsCollection = appReference.getTrainsCollection();
        this.carsCollection = appReference.getCarsCollection();
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
                retry++;
                System.out.println(e);
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while (homeStation == null && retry < MAX_RETRY);

        retry = 0;
        do {
            try {
                targetStation = this.stationsMap.searchStationByCode(Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: "));
            } catch (StationNotFoundException e) {
                retry++;
                System.out.println(e);
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while (targetStation == null);

        if (targetStation == null || homeStation == null) {
            System.out.println("Nie można utworzyć pociągu bez stacji startowej lub docelowej");
        } else {
            this.trainsCollection.addItem(new Train(homeStation, targetStation));
            System.out.println("Dodano pociąg");
        }
    }

    public void attachCar() {
        try {
            BaseCar car = this.carsCollection.getItemWithPrompt("Podaj ID wagonu");
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            train.attachCar(car);
            car.setIsAttachedTo(train);
            System.out.println("Przypisano wagon");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteTrain() {
        try {
            Train train = this.trainsCollection.getItemWithPrompt("Podaj ID pociągu");

            List<BaseCar> cars = train.getCars();
            for (BaseCar car : cars) {
                car.setIsAttachedTo(null);
            }
            ;

            trainsCollection.deleteItem(train.getID());
            System.out.println("Usunięto pociąg");
        } catch (ItemNotFoundException e) {
            System.out.println(e);
        }
    }
}
