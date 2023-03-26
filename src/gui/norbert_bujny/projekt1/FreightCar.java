package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class FreightCar extends BaseCar {
    private WayToLoadCargo wayToLoadCargo;
    private String cargoName;

    public FreightCar() {
        super(true, CarTypes.FREIGHT_CAR);
        this.initializeCar();
    }

    public FreightCar(CarTypes carType) {
        super(true, carType);
        this.initializeCar();
    }

    public FreightCar(FreightCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.wayToLoadCargo = otherCar.wayToLoadCargo;
            this.cargoName = otherCar.cargoName;
        }
    }

    public FreightCar(Boolean needsElectricity, CarTypes carType) {
        super(needsElectricity, carType);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<WayToLoadCargo> enumWrapper = new MenuCompatibleEnumWrapper<>(WayToLoadCargo.values());

        this.wayToLoadCargo = (WayToLoadCargo) Utilities.handleUserRequiredEnumInput("Sposób ładowania: ", enumWrapper).getChosenOption();
        this.cargoName = Utilities.handleUserRequiredInput("Nazwa towaru: ");
    }

    @Override
    public BaseCar clone() {
        return new FreightCar(this);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nSposób ładowania: " + this.wayToLoadCargo
                + ",\nNazwa towaru: " + this.cargoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FreightCar that = (FreightCar) o;
        return wayToLoadCargo == that.wayToLoadCargo && Objects.equals(cargoName, that.cargoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wayToLoadCargo, cargoName);
    }
}
