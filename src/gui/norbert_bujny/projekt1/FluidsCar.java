package gui.norbert_bujny.projekt1;

public class FluidsCar extends FreightCar {
    private double fluidDensity;
    private boolean hasIntegratedTube;

    public FluidsCar() {
        super();
        this.initializeCar();
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
}
