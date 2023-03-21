package gui.norbert_bujny.projekt1;

import java.util.Set;

public class LuggagePostCar extends PostCar {
    private Set<LuggageTypes> allowedLuggageTypes;

    public LuggagePostCar() {
        super(false);
    }

    public void setAllowedLuggageTypes(Set<LuggageTypes> allowedLuggageTypes) {
        this.allowedLuggageTypes = allowedLuggageTypes;
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nDozwolone typy bagażu: " + this.allowedLuggageTypes;
    }
}
