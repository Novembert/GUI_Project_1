package gui.norbert_bujny.projekt1;

public class FreightCar extends BaseCar {
    private WayToLoadCargo wayToLoadCargo;
    private String cargoName;

    public FreightCar() {
        super(true);
        this.initializeCar();
    }

    public FreightCar(Boolean needsElectricity) {
        super(needsElectricity);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<WayToLoadCargo> enumWrapper = new MenuCompatibleEnumWrapper<>(WayToLoadCargo.values());

        this.wayToLoadCargo = (WayToLoadCargo) Utilities.handleUserRequiredEnumInput("Sposób ładowania: ", enumWrapper).getChosenOption();
        this.cargoName = Utilities.handleUserRequiredInput("Nazwa towaru: ");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nSposób ładowania: " + this.wayToLoadCargo
                + ",\nNazwa towaru: " + this.cargoName;
    }
}
