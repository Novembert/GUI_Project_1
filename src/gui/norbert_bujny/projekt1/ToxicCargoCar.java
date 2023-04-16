package gui.norbert_bujny.projekt1;

import java.util.Objects;
import java.util.Set;

public class ToxicCargoCar extends HeavyFreightCar {
    private PollutionLevel pollutionLevel;
    public String howToNeutralize;

    public ToxicCargoCar() {
        super(CarTypes.TOXIC_CARGO_CAR);
        this.initializeCar();
    }

    public ToxicCargoCar(CarTypes carType) {
        super(carType);
        this.initializeCar();
    }

    public ToxicCargoCar(double netWeight,
                         double grossWeight,
                         WayToLoadCargo wayToLoadCargo,
                         String cargoName,
                         Set<CargoProtection> cargoProtection,
                         PollutionLevel pollutionLevel,
                         String howToNeutralize) {
        super(CarTypes.TOXIC_CARGO_CAR, netWeight, grossWeight, wayToLoadCargo, cargoName, cargoProtection);
        this.pollutionLevel = pollutionLevel;
        this.howToNeutralize = howToNeutralize;
    }

    public ToxicCargoCar(
            CarTypes carType,
            double netWeight,
            double grossWeight,
            WayToLoadCargo wayToLoadCargo,
            String cargoName,
            Set<CargoProtection> cargoProtection,
            PollutionLevel pollutionLevel,
            String howToNeutralize) {
        super(carType, netWeight, grossWeight, wayToLoadCargo, cargoName, cargoProtection);
        this.pollutionLevel = pollutionLevel;
        this.howToNeutralize = howToNeutralize;
    }

    public ToxicCargoCar(ToxicCargoCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.pollutionLevel = otherCar.pollutionLevel;
            this.howToNeutralize = otherCar.howToNeutralize;
        }
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

    @Override
    public BaseCar clone() {
        return new ToxicCargoCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ToxicCargoCar that = (ToxicCargoCar) o;
        return pollutionLevel == that.pollutionLevel && Objects.equals(howToNeutralize, that.howToNeutralize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pollutionLevel, howToNeutralize);
    }
}
