package gui.norbert_bujny.projekt1;

public class PathNotFoundException extends Exception {
    public PathNotFoundException() {
        super("Nie znaleziono trasy");
    }
}
