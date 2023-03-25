package gui.norbert_bujny.projekt1;

import java.util.Set;

public class LuggagePostCar extends PostCar {
    private Set<LuggageTypes> allowedLuggageTypes;

    public LuggagePostCar() {
        super(false);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<LuggageTypes> enumWrapper = new MenuCompatibleEnumWrapper<>(LuggageTypes.values());

        this.allowedLuggageTypes = Utilities.handleUserRequiredEnumSetInput("Dozwolone typy bagażu:", enumWrapper).getChosenOptions();
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nDozwolone typy bagażu: " + this.allowedLuggageTypes;
    }
}
