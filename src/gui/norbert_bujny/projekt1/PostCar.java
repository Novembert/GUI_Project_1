package gui.norbert_bujny.projekt1;

import java.util.Set;

public class PostCar extends BaseCar {
    private int maxParcelsCount;
    private Set<ParcelsTypes> allowedParcelsTypes;

    public PostCar() {
        super(true);
        this.initializeCar();
    }

    public PostCar(Boolean needsElectricity) {
        super(needsElectricity);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<ParcelsTypes> enumWrapper = new MenuCompatibleEnumWrapper<>(ParcelsTypes.values());

        this.allowedParcelsTypes = Utilities.handleUserRequiredEnumSetInput("Obsługiwane typy przesyłek:", enumWrapper).getChosenOptions();
        this.maxParcelsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna ilość przesyłek: "));
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\n Obsługiwane typy przesyłek: " + this.allowedParcelsTypes
                + ",\n Maksymalna ilość przesyłek: " + this.maxParcelsCount;
    }
}
