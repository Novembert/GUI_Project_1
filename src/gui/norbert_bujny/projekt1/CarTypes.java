package gui.norbert_bujny.projekt1;

public enum CarTypes {
    DEFAULT_CAR("Wagon domyślny"),
    BUFFET_CAR("Wagon restauracyjny"),
    PASSENGER_CAR("Wagon pasażerski"),
    POST_CAR("Wagon pocztowy"),
    LUGGAGE_POST_CAR("Wagon bagażowo-pocztowy"),
    FREIGHT_CAR("Wagon towarowy podstawowy"),
    HEAVY_FREIGHT_CAR("Wagon towarowy cięzki"),
    REFRIGERATOR_CAR("Wagon chłodniczy"),
    FLUIDS_CAR("Wagon na materiały ciekłe"),
    GAS_CAR("Wagon na materiały gazowe"),
    EXPLOSIVES_CAR("Wagon na materiały wybuchowe"),
    TOXIC_CARGO_CAR("Wagon na materiały toksyczne"),
    FLUID_TOXIC_CARGO_CAR("Wagon na ciekłe materiały toksyczne");

    private final String value;

    private CarTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
