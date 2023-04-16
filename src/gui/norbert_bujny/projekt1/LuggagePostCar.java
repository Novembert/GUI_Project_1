package gui.norbert_bujny.projekt1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LuggagePostCar extends PostCar {
    private Set<LuggageTypes> allowedLuggageTypes;

    public LuggagePostCar() {
        super(false, CarTypes.LUGGAGE_POST_CAR);
        this.initializeCar();
    }

    public LuggagePostCar(CarTypes carType) {
        super(false, carType);
        this.initializeCar();
    }

    public LuggagePostCar(double netWeight,
                          double grossWeight,
                          int maxParcelsCount,
                          Set<ParcelsTypes> allowedParcelsTypes,
                          Set<LuggageTypes> allowedLuggageTypes) {
        super(false, CarTypes.LUGGAGE_POST_CAR, netWeight, grossWeight, maxParcelsCount, allowedParcelsTypes);
        this.allowedLuggageTypes = allowedLuggageTypes;
    }

    public LuggagePostCar(LuggagePostCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.allowedLuggageTypes = new HashSet<>();
            this.allowedLuggageTypes.addAll(otherCar.allowedLuggageTypes);
        }
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<LuggageTypes> enumWrapper = new MenuCompatibleEnumWrapper<>(LuggageTypes.values());

        this.allowedLuggageTypes = Utilities.handleUserRequiredEnumSetInput("Dozwolone typy bagażu:", enumWrapper).getChosenOptions();
    }

    @Override
    public BaseCar clone() {
        return new LuggagePostCar(this);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nDozwolone typy bagażu: " + this.allowedLuggageTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LuggagePostCar that = (LuggagePostCar) o;
        return Objects.equals(allowedLuggageTypes, that.allowedLuggageTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), allowedLuggageTypes);
    }
}
