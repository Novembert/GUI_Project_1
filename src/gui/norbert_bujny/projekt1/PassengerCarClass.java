package gui.norbert_bujny.projekt1;

public enum PassengerCarClass {
    FIRST("Pierwsza"),
    SECOND("Druga");

    private final String value;
    private PassengerCarClass(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
