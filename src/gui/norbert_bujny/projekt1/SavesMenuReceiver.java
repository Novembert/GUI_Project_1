package gui.norbert_bujny.projekt1;

import java.util.List;

public class SavesMenuReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;
    private CarsCollection carsCollection;
    private TrainsCollection trainsCollection;
    private TrainCarsMap trainCarsMap;
    private Saves saves;

    public SavesMenuReceiver(App appReference) {
        super(appReference);
        this.carsCollection = appReference.getCarsCollection();
        this.stationsMap = appReference.getStationsMap();
        this.trainsCollection = appReference.getTrainsCollection();
        this.saves = appReference.getSaves();
        this.trainCarsMap = appReference.getTrainCarsMap();
    }

    public void saveState() {
        String saveName;
        do {
            saveName = Utilities.handleUserRequiredInput("Podaj nazwę zapisu: ");
        } while (this.saves.findIfSaveExists(saveName));


        this.saves.createNewSave(saveName, stationsMap, carsCollection, trainsCollection, trainCarsMap);
    }

    public void readState() {
        List<String> savesNames = this.saves.listExistingSaves();

        this.saves.readSave(Utilities.handleUserRequiredStringListInput("Wybierz zapis (podaj odpowiadającą mu liczbę): ", savesNames));
    }

    public void overrideState() {
        List<String> savesNames = this.saves.listExistingSaves();
        this.saves.overrideSave(Utilities.handleUserRequiredStringListInput("Wybierz zapis (podaj odpowiadającą mu liczbę): ", savesNames), stationsMap, carsCollection, trainsCollection, trainCarsMap);
    }
}
