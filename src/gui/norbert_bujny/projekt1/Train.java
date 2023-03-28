package gui.norbert_bujny.projekt1;

import java.io.Serializable;

public class Train implements IdRepresentedItem, Serializable {
    private String ID;

    public Train() {
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.CAR_ID.toString());
    }

    @Override
    public String getID() {
        return this.ID;
    }
}
