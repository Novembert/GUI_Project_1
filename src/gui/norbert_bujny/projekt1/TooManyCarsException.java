package gui.norbert_bujny.projekt1;

public class TooManyCarsException extends Exception {
    public TooManyCarsException() {
        super("Pociąg ma już za dużo wagonów. Nie można przypisać kolejnego wagonu");
    }
}
