package gui.norbert_bujny.projekt1;

public enum TrainRideState {
    CREATED("Nowy pociąg"),

    WAITING_AT_FINAL_STATION("Czeka na jednej ze stacji końcowych"),
    ARRIVED_TO_FINAL_STATION("Dotarł do jednej ze stacji końcowych"),

    WAITING_AT_STATION("Czeka na stacji pośredniej"),
    ARRIVED_TO_STATION("Dotarł do stacji pośredniej"),

    WAITING_IN_QUEUE("Czeka w kolejce na dostęp do toru"),
    RUNNING("Jedzie"),
    READY_TO_GO("Gotowy do jazdy"),
    READY_TO_START_NEW_TRAVEL("Gotowy do rozpoczęcia trasy"),
    STOPPED("Zatrzymany w związku z awarią");

    private final String value;

    private TrainRideState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
