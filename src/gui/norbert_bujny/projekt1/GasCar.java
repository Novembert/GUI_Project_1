package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class GasCar extends FreightCar {
    private double gasDensity;
    private Boolean isDangerous;

    public GasCar() {
        super(false, CarTypes.GAS_CAR);
        this.initializeCar();
    }

    public GasCar(CarTypes carType) {
        super(false, carType);
        this.initializeCar();
    }

    public GasCar(GasCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.isDangerous = otherCar.isDangerous;
            this.gasDensity = otherCar.gasDensity;
        }
    }

    private void initializeCar() {
        this.gasDensity = Utilities.handleUserRequiredInputDouble("Gęstość gazu: ");
        this.isDangerous = Utilities.handleUserRequiredBooleanInput("Czy przewożony gaz jest niebezpieczny?");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nGęstość gazu: " + this.gasDensity
                + ",\nNiebezpieczeństwo: " + Utilities.getPolishTranslationForBooleanValues(this.isDangerous);
    }

    @Override
    public BaseCar clone() {
        return new GasCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GasCar gasCar = (GasCar) o;
        return Double.compare(gasCar.gasDensity, gasDensity) == 0 && Objects.equals(isDangerous, gasCar.isDangerous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gasDensity, isDangerous);
    }
}
