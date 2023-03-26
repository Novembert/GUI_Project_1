package gui.norbert_bujny.projekt1;

import java.util.Objects;

public abstract class BaseCar {
    //    TODO kim jest nadawca wagonu?
    private double netWeight;
    private double grossWeight;
    private Boolean needsElectricity;
    private String ID;
    private CarTypes carType;

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

    ;

    public BaseCar(BaseCar otherCar) {
        if (otherCar != null) {
            this.netWeight = otherCar.netWeight;
            this.grossWeight = otherCar.grossWeight;
            this.needsElectricity = otherCar.needsElectricity;
            this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
            this.carType = otherCar.carType;
        }
    }

    public String getID() {
        return ID;
    }

    public abstract BaseCar clone();

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
        this.netWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Waga netto: "));
        this.grossWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Waga brutto: "));
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ",\nTyp wagonu: " + this.carType +
                ",\nMasa netto: " + this.netWeight +
                ",\nMasa brutto: " + this.grossWeight +
                ",\nCzy wymaga podłączenia do sieci elektrycznej lokomotywy: " + Utilities.getPolishTranslationForBooleanValues(this.needsElectricity);
    }
}
