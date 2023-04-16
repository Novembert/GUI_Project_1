package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class FluidsCar extends FreightCar {
    private double fluidDensity;
    private boolean hasIntegratedTube;

    public FluidsCar() {
        super(false, CarTypes.FLUIDS_CAR);
        this.initializeCar();
    }

    public FluidsCar(CarTypes carType) {
        super(false, carType);
        this.initializeCar();
    }

    public FluidsCar(double netWeight,
                     double grossWeight,
                     WayToLoadCargo wayToLoadCargo,
                     String cargoName,
                     double fluidDensity,
                     boolean hasIntegratedTube) {
        super(false, CarTypes.FLUIDS_CAR, netWeight, grossWeight, wayToLoadCargo, cargoName);
        this.fluidDensity = fluidDensity;
        this.hasIntegratedTube = hasIntegratedTube;
    }

    public FluidsCar(FluidsCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.fluidDensity = otherCar.fluidDensity;
            this.hasIntegratedTube = otherCar.hasIntegratedTube;
        }
    }

    private void initializeCar() {
        this.fluidDensity = Utilities.handleUserRequiredInputDouble("Gęstość cieczy: ");
        this.hasIntegratedTube = Utilities.handleUserRequiredBooleanInput("Czy ma zintegrowaną rurę do pompowania cieczy?");
    }

    @Override
    public BaseCar clone() {
        return new FluidsCar(this);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nGęstość cieczy: " + this.fluidDensity
                + ",\nMa zintegrowaną rurę do pompowania cieczy: " + Utilities.getPolishTranslationForBooleanValues(this.hasIntegratedTube);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FluidsCar fluidsCar = (FluidsCar) o;
        return Double.compare(fluidsCar.fluidDensity, fluidDensity) == 0 && hasIntegratedTube == fluidsCar.hasIntegratedTube;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fluidDensity, hasIntegratedTube);
    }
}
