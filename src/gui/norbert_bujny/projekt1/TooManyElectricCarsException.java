package gui.norbert_bujny.projekt1;

public class TooManyElectricCarsException extends Exception {
    public TooManyElectricCarsException() {
        super("Pociąg ma już za dużo wagonów wymagających podłączenia do sieci elektrycznej. Nie można przypisać kolejnego wagonu");
    }
}
