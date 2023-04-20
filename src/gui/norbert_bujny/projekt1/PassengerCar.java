package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class PassengerCar extends BaseCar implements Loadable {
    private Boolean hasToilet;
    private Integer seats;
    private Integer takenSeats;
    private PassengerCarClass carClass;
    private Boolean hasCompartments;
    private double expectedPassengerWeight;

    public PassengerCar() {
        super(true, CarTypes.PASSENGER_CAR);
        this.initializeCar();
    }

    public PassengerCar(double netWeight,
                        double grossWeight,
                        boolean hasToilet,
                        boolean hasCompartments,
                        int seats,
                        PassengerCarClass passengerCarClass) {
        super(true, CarTypes.PASSENGER_CAR, netWeight, grossWeight);
        this.takenSeats = 0;
        this.hasToilet = hasToilet;
        this.hasCompartments = hasCompartments;
        this.seats = seats;
        this.carClass = passengerCarClass;
        this.expectedPassengerWeight = this.calculateExpectedPassengerWeight();
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
            this.takenSeats = 0;
            this.carClass = otherCar.carClass;
            this.hasCompartments = otherCar.hasCompartments;
            this.expectedPassengerWeight = this.calculateExpectedPassengerWeight();
        }
    }

    public Command initLoadCargo() {
        int freeSeats = this.seats - this.takenSeats;
        System.out.println("Wolne miejsca: " + freeSeats + "/" + this.seats);

        int newPassengers = Utilities.handleUserRequiredInputInt("Ilu pasażerów ma wsiąść?");

        return new Command() {
            @Override
            public void execute() {
                takenSeats = newPassengers + takenSeats > seats ? seats : takenSeats + newPassengers;
                setNetWeight(expectedPassengerWeight * takenSeats);
            }
        };
    }

    public void loadCargo(int newPassengers) {
        takenSeats = newPassengers + takenSeats > seats ? seats : takenSeats + newPassengers;
        setNetWeight(expectedPassengerWeight * takenSeats);
    }

    public Command initUnloadCargo() {
        System.out.println("Zajęte miejsca: " + takenSeats + "/" + this.seats);

        int unloadPassengers = Utilities.handleUserRequiredInputInt("Ilu pasażerów ma wysiąść?");
        int reducedNumber = this.takenSeats - unloadPassengers;

        return new Command() {
            @Override
            public void execute() {
                takenSeats = reducedNumber < 0 ? 0 : reducedNumber;
                setNetWeight(expectedPassengerWeight * takenSeats);
            }
        };
    }

    public void unloadCargo(int unloadPassengers) {
        int reducedNumber = this.takenSeats - unloadPassengers;

        takenSeats = reducedNumber < 0 ? 0 : reducedNumber;
        setNetWeight(expectedPassengerWeight * takenSeats);
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<PassengerCarClass> enumWrapper = new MenuCompatibleEnumWrapper<>(PassengerCarClass.values());

        this.carClass = (PassengerCarClass) Utilities.handleUserRequiredEnumInput("Klasa: ", enumWrapper).getChosenOption();
        this.seats = Utilities.handleUserRequiredInputInt("Ilość miejsc siedzących: ");
        this.takenSeats = 0;
        this.hasCompartments = Utilities.handleUserRequiredBooleanInput("Czy ma przedziały?");
        this.hasToilet = Utilities.handleUserRequiredBooleanInput("Czy posiada toaletę?");
        this.expectedPassengerWeight = this.calculateExpectedPassengerWeight();
    }

    private double calculateExpectedPassengerWeight() {
        return (this.getGrossWeight() - this.getNetWeight()) / this.seats;
    }

    @Override
    public String toString() {
        return super.toString() +
                ",\nKlasa: " + this.carClass +
                ",\nIlość miejsc: " + this.seats +
                ",\nIlość zajętych miejsc: " + this.takenSeats +
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
