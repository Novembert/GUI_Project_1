package gui.norbert_bujny.projekt1;

public enum PollutionLevel {
    LOW("Małe"),
    MEDIUM("Średnie"),
    HIGH("Duże");

    private final String value;
    private PollutionLevel(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
