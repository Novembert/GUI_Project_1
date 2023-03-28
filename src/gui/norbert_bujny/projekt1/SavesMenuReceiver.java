package gui.norbert_bujny.projekt1;

import java.util.List;

public class SavesMenuReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;
    private CarsCollection carsCollection;
    private Saves saves;

    public SavesMenuReceiver(App appReference) {
        super(appReference);
        this.carsCollection = appReference.getCarsCollection();
        this.stationsMap = appReference.getStationsMap();
        this.saves = appReference.getSaves();
    }

    public void saveState() {
        String saveName;
        do {
            saveName = Utilities.handleUserRequiredInput("Podaj nazwę zapisu: ");
        } while (this.saves.findIfSaveExists(saveName));


        this.saves.createNewSave(saveName, stationsMap, carsCollection);
    }

    public void readState() {
        List<String> savesNames = this.saves.listExistingSaves();

        this.saves.readSave(Utilities.handleUserRequiredStringListInput("Wybierz zapis (podaj odpowiadającą mu liczbę): "));
    }

    public void overrideState() {
        String saveName = Utilities.handleUserRequiredInput("Podaj nazwę zapisu: ");
    }
}
