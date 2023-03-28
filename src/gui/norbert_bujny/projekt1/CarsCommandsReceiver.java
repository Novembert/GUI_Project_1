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
            throw new RuntimeException(e);
        }
    }

    public void initializeCarCopier() {
        String carToCopyID = Utilities.handleUserRequiredInput("Podaj ID wagonu");
        this.carsCollection.addItem(this.carsCollection.getItem(carToCopyID).clone());
    }

    public void initializeCarDelete() {
        String carToDeleteId = Utilities.handleUserRequiredInput("Podaj ID wagonu");
        this.carsCollection.deleteItem(carToDeleteId);
    }
}
