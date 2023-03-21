package gui.norbert_bujny.projekt1;

public class ToxicCargoCar extends HeavyFreightCar {
    private PollutionLevel pollutionLevel;
    public String howToNeutralize;

    @Override
    public String toString() {
        return super.toString()
                + ",\nPoziom skażenia: " + this.pollutionLevel
                + ",\nJak neutralizować: " + this.howToNeutralize;
    }
}
