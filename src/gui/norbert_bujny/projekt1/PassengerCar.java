package gui.norbert_bujny.projekt1;

public class PassengerCar extends BaseCar {
    private Boolean hasToilet;
    private Integer seats;
    private PassengerCarClass carClass;
    private Boolean hasCompartments;

    public PassengerCar() {
        super(true);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<PassengerCarClass> enumWrapper = new MenuCompatibleEnumWrapper<>(PassengerCarClass.values());

        this.carClass = (PassengerCarClass) Utilities.handleUserRequiredEnumInput("Klasa: ", enumWrapper).getChosenOption();
        this.seats = Integer.parseInt(Utilities.handleUserRequiredInput("Ilość miejsc siedzących: "));
        this.hasCompartments = Utilities.handleUserRequiredBooleanInput("Czy ma przedziały?");
        this.hasToilet = Utilities.handleUserRequiredBooleanInput("Czy posiada toaletę?");
        ;
    }

    @Override
    public String toString() {
        return super.toString() +
                ",\nKlasa: " + this.carClass +
                ",\nIlość miejsc: " + this.seats +
                ",\nPosiada przedziały: " + Utilities.getPolishTranslationForBooleanValues(hasCompartments) +
                ",\nPosiada toaletę: " + Utilities.getPolishTranslationForBooleanValues(hasToilet);
    }
}
