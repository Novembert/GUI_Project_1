package gui.norbert_bujny.projekt1;

public class AlreadyAddedException extends Exception {
    public AlreadyAddedException() {
        super("Już dodano taki obiekt");
    }
}
