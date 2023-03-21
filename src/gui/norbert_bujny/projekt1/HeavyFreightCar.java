package gui.norbert_bujny.projekt1;

import java.util.Set;

public class HeavyFreightCar extends FreightCar {
    private Set<CargoProtection> cargoProtection;

    public HeavyFreightCar() {
        super(false);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nZabezpieczenia: " + this.cargoProtection;
    }
}
