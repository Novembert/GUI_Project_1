package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class FreightCar extends BaseCar implements Loadable {
    private WayToLoadCargo wayToLoadCargo;
    private String cargoName;

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

    public FreightCar(double netWeight,
                      double grossWeight,
                      WayToLoadCargo wayToLoadCargo,
                      String cargoName) {
        super(false, CarTypes.FREIGHT_CAR, netWeight, grossWeight);
        this.wayToLoadCargo = wayToLoadCargo;
        this.cargoName = cargoName;
    }

    public FreightCar(Boolean needsElectricity,
                      CarTypes carType,
                      double netWeight,
                      double grossWeight,
                      WayToLoadCargo wayToLoadCargo,
                      String cargoName) {
        super(needsElectricity, carType, netWeight, grossWeight);
        this.wayToLoadCargo = wayToLoadCargo;
        this.cargoName = cargoName;
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<WayToLoadCargo> enumWrapper = new MenuCompatibleEnumWrapper<>(WayToLoadCargo.values());

        this.wayToLoadCargo = (WayToLoadCargo) Utilities.handleUserRequiredEnumInput("Sposób ładowania: ", enumWrapper).getChosenOption();
        this.cargoName = Utilities.handleUserRequiredInput("Nazwa towaru: ");
    }

    public Command initLoadCargo() {
        double freeWeight = this.getGrossWeight() - this.getNetWeight();
        System.out.println("Udźwig do wykorzystania: " + freeWeight + "/" + this.getGrossWeight());

        double newCargoWeight = Utilities.handleUserRequiredInputDouble("Ile towaru załadować?");

        return new Command() {
            @Override
            public void execute() {
                setNetWeight(newCargoWeight > freeWeight ? getGrossWeight() : newCargoWeight);
            }
        };
    }

    public Command initUnloadCargo() {
        System.out.println("Zajęty udźwig: " + getNetWeight() + "/" + getGrossWeight());

        double unloadWeight = Utilities.handleUserRequiredInputDouble("Ile towaru wyładować?");
        double reducedNumber = getNetWeight() - unloadWeight;

        return new Command() {
            @Override
            public void execute() {
                setNetWeight(reducedNumber < 0 ? 0 : reducedNumber);
            }
        };
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
