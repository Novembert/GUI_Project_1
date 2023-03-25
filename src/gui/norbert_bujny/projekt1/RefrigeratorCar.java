package gui.norbert_bujny.projekt1;

public class RefrigeratorCar extends FreightCar {
    private Boolean hasShelves;
    private double celsiusDegrees;

    public RefrigeratorCar() {
        super(true);
        this.initializeCar();
    }

    private void initializeCar() {
        this.hasShelves = Utilities.handleUserRequiredBooleanInput("Czy ma pólki? ");
        this.celsiusDegrees = Double.parseDouble(Utilities.handleUserRequiredInput("Temperatura (°C)"));
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nMa półki: " + Utilities.getPolishTranslationForBooleanValues(this.hasShelves)
                + ",\nTemperatura (°C): " + this.celsiusDegrees;
    }
}
