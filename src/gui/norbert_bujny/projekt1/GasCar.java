package gui.norbert_bujny.projekt1;

public class GasCar extends FreightCar {
    private double gasDensity;
    private Boolean isDangerous;

    public GasCar() {
        super(false);
        this.initializeCar();
    }

    private void initializeCar() {
        this.gasDensity = Double.parseDouble(Utilities.handleUserRequiredInput("Gęstość gazu: "));
        this.isDangerous = Utilities.handleUserRequiredBooleanInput("Czy przewożony gaz jest niebezpieczny?");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nGęstość gazu: " + this.gasDensity
                + ",\nNiebezpieczeństwo: " + Utilities.getPolishTranslationForBooleanValues(this.isDangerous);
    }
}
