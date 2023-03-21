package gui.norbert_bujny.projekt1;

public class RefrigeratorCar extends FreightCar {
    private Boolean hasShelves;
    private Number celsiusDegrees;

    public RefrigeratorCar() {
        super(true);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nMa półki: " + Utilities.getPolishTranslationForBooleanValues(this.hasShelves)
                + ",\nTemperatura (°C): " + this.celsiusDegrees;
    }
}
