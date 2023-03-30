package gui.norbert_bujny.projekt1;

public enum LuggageTypes {
    BIKE("Rowery"),
    FURNITURE("Meble"),
    ANIMAL("Zwierzęta"),
    MUSICAL_INSTRUMENT("Instumenty muzyczne");

    private final String value;
    private LuggageTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
