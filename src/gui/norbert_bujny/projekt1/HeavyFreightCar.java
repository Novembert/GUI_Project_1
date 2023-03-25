package gui.norbert_bujny.projekt1;

import java.util.Set;

public class HeavyFreightCar extends FreightCar {
    private Set<CargoProtection> cargoProtection;

    public HeavyFreightCar() {
        super(false);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<CargoProtection> enumWrapper = new MenuCompatibleEnumWrapper<>(CargoProtection.values());

        this.cargoProtection = Utilities.handleUserRequiredEnumSetInput("", enumWrapper).getChosenOptions();
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nZabezpieczenia: " + this.cargoProtection;
    }
}
