package gui.norbert_bujny.projekt1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PostCar extends BaseCar {
    private int maxParcelsCount;
    private Set<ParcelsTypes> allowedParcelsTypes;

    public PostCar() {
        super(true, CarTypes.POST_CAR);
        this.initializeCar();
    }

    public PostCar(Boolean needsElectricity, CarTypes carType) {
        super(needsElectricity, carType);
        this.initializeCar();
    }

    public PostCar(PostCar otherCar) {
        super(otherCar);
        this.maxParcelsCount = otherCar.maxParcelsCount;
        this.allowedParcelsTypes = new HashSet<>();
        this.allowedParcelsTypes.addAll(otherCar.allowedParcelsTypes);
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<ParcelsTypes> enumWrapper = new MenuCompatibleEnumWrapper<>(ParcelsTypes.values());

        this.allowedParcelsTypes = Utilities.handleUserRequiredEnumSetInput("Obsługiwane typy przesyłek:", enumWrapper).getChosenOptions();
        this.maxParcelsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna ilość przesyłek: "));
    }

    @Override
    public BaseCar clone() {
        return new PostCar(this);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\n Obsługiwane typy przesyłek: " + this.allowedParcelsTypes
                + ",\n Maksymalna ilość przesyłek: " + this.maxParcelsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostCar postCar = (PostCar) o;
        return maxParcelsCount == postCar.maxParcelsCount && Objects.equals(allowedParcelsTypes, postCar.allowedParcelsTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxParcelsCount, allowedParcelsTypes);
    }
}
