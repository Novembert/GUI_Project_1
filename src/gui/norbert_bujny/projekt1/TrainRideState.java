package gui.norbert_bujny.projekt1;

public enum TrainRideState {
    CREATED,

    WAITING_AT_FINAL_STATION,
    ARRIVED_TO_FINAL_STATION,

    WAITING_AT_STATION,
    ARRIVED_TO_STATION,

    WAITING_IN_QUEUE,
    RUNNING,
    READY_TO_GO,
    READY_TO_START_NEW_TRAVEL,
    STOPPED
}
