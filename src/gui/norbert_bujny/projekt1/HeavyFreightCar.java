package gui.norbert_bujny.projekt1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HeavyFreightCar extends FreightCar {
    private Set<CargoProtection> cargoProtection;

    public HeavyFreightCar() {
        super(false, CarTypes.HEAVY_FREIGHT_CAR);
        this.initializeCar();
    }

    public HeavyFreightCar(CarTypes carType) {
        super(false, carType);
        this.initializeCar();
    }

    public HeavyFreightCar(HeavyFreightCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.cargoProtection = new HashSet<>();
            this.cargoProtection.addAll(otherCar.cargoProtection);
        }
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<CargoProtection> enumWrapper = new MenuCompatibleEnumWrapper<>(CargoProtection.values());

        this.cargoProtection = Utilities.handleUserRequiredEnumSetInput("Zabezpieczenia: ", enumWrapper).getChosenOptions();
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nZabezpieczenia: " + this.cargoProtection;
    }

    @Override
    public BaseCar clone() {
        return new HeavyFreightCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HeavyFreightCar that = (HeavyFreightCar) o;
        return Objects.equals(cargoProtection, that.cargoProtection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoProtection);
    }
}
