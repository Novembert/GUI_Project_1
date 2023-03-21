package gui.norbert_bujny.projekt1;

public class FluidsCar extends FreightCar {
    private Number fluidDensity;
    private Boolean hasIntegratedTube;

    @Override
    public String toString() {
        return super.toString()
                + ",\nGęstość cieczy: " + this.fluidDensity
                + ",\nMa zintegrowaną rurę do pompowania cieczy: " + Utilities.getPolishTranslationForBooleanValues(this.hasIntegratedTube);
    }
}
