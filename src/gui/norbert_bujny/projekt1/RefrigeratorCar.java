package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class RefrigeratorCar extends FreightCar {
    private Boolean hasShelves;
    private double celsiusDegrees;

    public RefrigeratorCar() {
        super(true, CarTypes.REFRIGERATOR_CAR);
        this.initializeCar();
    }

    public RefrigeratorCar(CarTypes carType) {
        super(true, carType);
        this.initializeCar();
    }

    public RefrigeratorCar(RefrigeratorCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.hasShelves = otherCar.hasShelves;
            this.celsiusDegrees = otherCar.celsiusDegrees;
        }
    }

    private void initializeCar() {
        this.hasShelves = Utilities.handleUserRequiredBooleanInput("Czy ma pólki? ");
        this.celsiusDegrees = Utilities.handleUserRequiredInputDouble("Temperatura (°C)");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nMa półki: " + Utilities.getPolishTranslationForBooleanValues(this.hasShelves)
                + ",\nTemperatura (°C): " + this.celsiusDegrees;
    }

    @Override
    public BaseCar clone() {
        return new RefrigeratorCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RefrigeratorCar that = (RefrigeratorCar) o;
        return Double.compare(that.celsiusDegrees, celsiusDegrees) == 0 && Objects.equals(hasShelves, that.hasShelves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasShelves, celsiusDegrees);
    }
}
