package gui.norbert_bujny.projekt1;

public class FreightCar extends Car {
    private WayToLoadCargo wayToLoadCargo;
    private String cargoName;

    public FreightCar() {
        super(true);
    }

    public FreightCar(Boolean needsElectricity) {
        super(needsElectricity);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nSposób ładowania: " + this.wayToLoadCargo
                + ",\nNazwa towaru: " + this.cargoName;
    }
}
