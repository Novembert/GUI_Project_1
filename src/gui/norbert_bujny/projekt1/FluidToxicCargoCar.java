package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class FluidToxicCargoCar extends ToxicCargoCar {

    private double fluidDensity;
    private boolean hasIntegratedTube;

    public FluidToxicCargoCar() {
        super(CarTypes.FLUID_TOXIC_CARGO_CAR);
        this.initializeCar();
    }

    public FluidToxicCargoCar(CarTypes carType) {
        super(carType);
        this.initializeCar();
    }

    public FluidToxicCargoCar(FluidToxicCargoCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.fluidDensity = otherCar.fluidDensity;
            this.hasIntegratedTube = otherCar.hasIntegratedTube;
        }
    }

    @Override
    public BaseCar clone() {
        return new FluidToxicCargoCar(this);
    }

    private void initializeCar() {
        this.fluidDensity = Double.parseDouble(Utilities.handleUserRequiredInput("Gęstość cieczy: "));
        this.hasIntegratedTube = Utilities.handleUserRequiredBooleanInput("Czy ma zintegrowaną rurę do pompowania cieczy?");
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
        FluidToxicCargoCar that = (FluidToxicCargoCar) o;
        return Double.compare(that.fluidDensity, fluidDensity) == 0 && hasIntegratedTube == that.hasIntegratedTube;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fluidDensity, hasIntegratedTube);
    }
}
