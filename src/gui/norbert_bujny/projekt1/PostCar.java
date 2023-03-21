package gui.norbert_bujny.projekt1;

import java.util.Set;

public class PostCar extends Car {
   private Integer maxParcelsCount;
   private Set<ParcelsTypes> allowedParcelsTypes;

    public PostCar() {
        super(true);
    }

    public PostCar(Boolean needsElectricity) {
        super(needsElectricity);
    }

    public void setAllowedParcelsTypes(Set<ParcelsTypes> allowedParcelsTypes) {
        this.allowedParcelsTypes = allowedParcelsTypes;
    }

    public void setMaxParcelsCount(Integer maxParcelsCount) {
        this.maxParcelsCount = maxParcelsCount;
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\n Obsługiwane typy przesyłek: " + this.allowedParcelsTypes
                + ",\n Maksymalna ilość przesyłek: " + this.maxParcelsCount;
    }
}
