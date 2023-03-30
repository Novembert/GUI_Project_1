package gui.norbert_bujny.projekt1;

public enum ParcelsTypes{
    LARGE("Duże"),
    MEDIUM("Średnie"),
    SMALL("Małe"),
    ENVELOPE("Koperty");

    private final String value;
    private ParcelsTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
