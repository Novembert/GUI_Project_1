package gui.norbert_bujny.projekt1;

public class TooHeavyCarException extends Exception {
    public TooHeavyCarException() {
        super("Wagon jest za ciężki aby dodać go do składu pociągu.");
    }
}
