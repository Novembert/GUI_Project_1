package gui.norbert_bujny.projekt1;

import java.util.List;

public class PassengerCar extends Car {
    private Boolean hasToilet;
    private Integer seats;
    private PassengerCarClass carClass;
    private Boolean hasCompartments;

    public PassengerCar() {
        super(true);
    }

    public void setHasToilet(Boolean hasToilet) {
        this.hasToilet = hasToilet;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public void setCarClass(PassengerCarClass carClass) {
        this.carClass = carClass;
    }

    public void setHasCompartments(Boolean hasCompartments) {
        this.hasCompartments = hasCompartments;
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
