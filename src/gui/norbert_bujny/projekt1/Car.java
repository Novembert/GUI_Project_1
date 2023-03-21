package gui.norbert_bujny.projekt1;

public class Car {
//    TODO kim jest nadawca wagonu?
    private Number netWeight;
    private Number grossWeight;
    private Boolean needsElectricity;
    private Number ID;

    public Car(Boolean needsElectricity){
        this.needsElectricity = needsElectricity;
    };

    public void setNetWeight(Number netWeight) {
        this.netWeight = netWeight;
    }

    public void setGrossWeight(Number grossWeight) {
        this.grossWeight = grossWeight;
    }

    public void setNeedsElectricity(Boolean needsElectricity) {
        this.needsElectricity = needsElectricity;
    }

    public void setID(Number ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID +
                ",\nMasa netto: " + this.netWeight +
                ",\nMasa brutto: " + this.grossWeight +
                ",\nCzy wymaga podłączenia do sieci elektrycznej lokomotywy: " + Utilities.getPolishTranslationForBooleanValues(this.needsElectricity);
    }
}
