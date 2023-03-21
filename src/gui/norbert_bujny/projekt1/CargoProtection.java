package gui.norbert_bujny.projekt1;

public enum CargoProtection {
    SAFETY_ROPES("Liny"),
    SAFETY_NET("Siatka"),
    SECURITY_BARS("Metalowe kraty");

    private String value;

    private CargoProtection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
