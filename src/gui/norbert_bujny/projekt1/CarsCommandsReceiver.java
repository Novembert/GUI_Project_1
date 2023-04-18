package gui.norbert_bujny.projekt1;

public class CarsCommandsReceiver extends MenuCommandsReceiver {
    private CarsCollection carsCollection;
    private CarTypesToObjectsMap carsTypes;

    public CarsCommandsReceiver(App appReference) {
        super(appReference);
        this.carsCollection = appReference.getCarsCollection();
        this.carsTypes = CarTypesToObjectsMap.getInstance();
    }

    public void printCarsList() {
        System.out.println(this.carsCollection.getItemsList());
    }

    public void initializeCarsCreator() {
        MenuCompatibleEnumWrapper<CarTypes> enumWrapper = new MenuCompatibleEnumWrapper<>(CarTypes.values());

        CarTypes chosenCarType = (CarTypes) Utilities.handleUserRequiredEnumSetInput("Typ wagonu: ", enumWrapper).getChosenOption();
        try {
            this.carsCollection.addItem((BaseCar) Class.forName(this.carsTypes.map.get(chosenCarType)).getConstructor().newInstance());
        } catch (Exception e) {
            System.out.println("Nie udało się utworzyć wagonu podanego typu");
        }
    }

    public void initializeCarCopier() {
        try {
            BaseCar carToCopy = null;
            carToCopy = this.carsCollection.getItemWithPrompt("Podaj ID wagonu");
            BaseCar copiedCar = this.runCarCopier(carToCopy);
            System.out.println("Skopiowano wagon. ID wagonu: " + copiedCar.getID());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public BaseCar runCarCopier(BaseCar carToCopy) {
        BaseCar copiedCar = carToCopy.clone();
        this.carsCollection.addItem(copiedCar);
        return copiedCar;
    }

    public void initializeCarDelete() {
        try {
            BaseCar carToDelete = this.carsCollection.getItemWithPrompt("Podaj ID wagonu");
            this.runCarDelete(carToDelete);
            System.out.println("Usunięto wagon");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runCarDelete(BaseCar carToDelete) {
        this.carsCollection.deleteItem(carToDelete.getID());
        TrainCarsMap.getInstance().removeCar(carToDelete);
    }
}
