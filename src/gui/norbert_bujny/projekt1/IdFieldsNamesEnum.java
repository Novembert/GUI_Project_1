package gui.norbert_bujny.projekt1;

public enum IdFieldsNamesEnum {
    CAR_ID("carID"),
    TRAIN_ID("trainID");

    private String value;

    private IdFieldsNamesEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
