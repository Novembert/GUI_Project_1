package gui.norbert_bujny.projekt1;

public enum WayToLoadCargo {
    SIDE_LOAD("Od boku"),
    TOP_LOAD("Od góry");

    private final String value;
    private WayToLoadCargo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
