package gui.norbert_bujny.projekt1;

public class BaseCar {
    //    TODO kim jest nadawca wagonu?
    private double netWeight;
    private double grossWeight;
    private Boolean needsElectricity;
    private String ID;

    public BaseCar(Boolean needsElectricity) {
        this.needsElectricity = needsElectricity;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
        this.initializeCar();
    }

    ;

    private void initializeCar() {
        this.netWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Waga netto: "));
        this.grossWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Waga brutto: "));
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ",\nMasa netto: " + this.netWeight +
                ",\nMasa brutto: " + this.grossWeight +
                ",\nCzy wymaga podłączenia do sieci elektrycznej lokomotywy: " + Utilities.getPolishTranslationForBooleanValues(this.needsElectricity);
    }
}
