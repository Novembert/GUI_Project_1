package gui.norbert_bujny.projekt1;

public enum PotentialExplosionPower {
    LOW("(0; 4kt)"),
    MEDIUM("<4kt; 8kt)"),
    HIGH("<8kt; ∞)");

    private final String value;
    private PotentialExplosionPower(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
