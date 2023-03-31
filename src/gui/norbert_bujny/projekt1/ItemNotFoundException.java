package gui.norbert_bujny.projekt1;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("Nie znaleziono przedmiotu");
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
