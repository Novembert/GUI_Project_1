package gui.norbert_bujny.projekt1;

public class GasCar extends FreightCar {
    private Number gasDensity;
    private Boolean isDangerous;

    @Override
    public String toString() {
        return super.toString()
                + ",\nGęstość gazu: " + this.gasDensity
                + ",\nNiebezpieczeństwo: " + Utilities.getPolishTranslationForBooleanValues(this.isDangerous);
    }
}
