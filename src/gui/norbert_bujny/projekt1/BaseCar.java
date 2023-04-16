package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseCar implements IdRepresentedItem, Serializable {
    private double netWeight;
    private double grossWeight;
    private boolean needsElectricity;
    private String ID;
    private CarTypes carType;

    public BaseCar(
            Boolean needsElectricity,
            CarTypes carType,
            double netWeight,
            double grossWeight) {
        if (carType == null) {
            this.carType = CarTypes.DEFAULT_CAR;
        } else {
            this.carType = carType;
        }
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
        this.needsElectricity = needsElectricity;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
    }

    public BaseCar(Boolean needsElectricity, CarTypes carType) {
        if (carType == null) {
            this.carType = CarTypes.DEFAULT_CAR;
        } else {
            this.carType = carType;
        }
        this.needsElectricity = needsElectricity;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
        this.initializeCar();
    }

    public BaseCar(BaseCar otherCar) {
        if (otherCar != null) {
            this.netWeight = otherCar.netWeight;
            this.grossWeight = otherCar.grossWeight;
            this.needsElectricity = otherCar.needsElectricity;
            this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
            this.carType = otherCar.carType;
        }
    }

    @Override
    public String getID() {
        return ID;
    }

    public abstract BaseCar clone();

    public boolean getNeedsElectricity() {
        return this.needsElectricity;
    }

    public double getGrossWeight() {
        return this.grossWeight;
    }

    public void setNetWeight(double weight) {
        this.netWeight = weight;
    }

    public double getNetWeight() {
        return this.netWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCar baseCar = (BaseCar) o;
        return Double.compare(baseCar.netWeight, netWeight) == 0 && Double.compare(baseCar.grossWeight, grossWeight) == 0 && Objects.equals(needsElectricity, baseCar.needsElectricity) && Objects.equals(ID, baseCar.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netWeight, grossWeight, needsElectricity, ID);
    }

    private void initializeCar() {
        this.netWeight = Utilities.handleUserRequiredInputDouble("Waga netto: ");
        this.grossWeight = Utilities.handleUserRequiredInputDouble("Waga brutto: ");
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ",\nTyp wagonu: " + this.carType +
                ",\nMasa netto: " + this.netWeight +
                ",\nMasa brutto: " + this.grossWeight +
                ",\nCzy wymaga podłączenia do sieci elektrycznej lokomotywy: " + Utilities.getPolishTranslationForBooleanValues(this.needsElectricity);
    }

    public String toShortString() {
        return "[" + this.ID + "] " + this.carType + (this.needsElectricity ? "[EL]" : "");
    }
}
