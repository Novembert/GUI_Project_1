package gui.norbert_bujny.projekt1;

public class ToxicCargoCar extends HeavyFreightCar {
    private PollutionLevel pollutionLevel;
    public String howToNeutralize;

    public ToxicCargoCar() {
        super();
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<PollutionLevel> enumWrapper = new MenuCompatibleEnumWrapper<>(PollutionLevel.values());

        this.pollutionLevel = (PollutionLevel) Utilities.handleUserRequiredEnumInput("Poziom skażenia: ", enumWrapper).getChosenOption();
        this.howToNeutralize = Utilities.handleUserRequiredInput("Jak neutralizować? ");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nPoziom skażenia: " + this.pollutionLevel
                + ",\nJak neutralizować: " + this.howToNeutralize;
    }
}
