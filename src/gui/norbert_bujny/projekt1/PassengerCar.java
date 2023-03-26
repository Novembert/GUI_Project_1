package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class PassengerCar extends BaseCar {
    private Boolean hasToilet;
    private Integer seats;
    private PassengerCarClass carClass;
    private Boolean hasCompartments;

    public PassengerCar() {
        super(true, CarTypes.PASSENGER_CAR);
        this.initializeCar();
    }

    public PassengerCar(CarTypes carType) {
        super(true, carType);
        this.initializeCar();
    }

    public PassengerCar(PassengerCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.hasToilet = otherCar.hasToilet;
            this.seats = otherCar.seats;
            this.carClass = otherCar.carClass;
            this.hasCompartments = otherCar.hasCompartments;
        }
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

    @Override
    public BaseCar clone() {
        return new PassengerCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerCar that = (PassengerCar) o;
        return Objects.equals(hasToilet, that.hasToilet) && Objects.equals(seats, that.seats) && carClass == that.carClass && Objects.equals(hasCompartments, that.hasCompartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasToilet, seats, carClass, hasCompartments);
    }
}
