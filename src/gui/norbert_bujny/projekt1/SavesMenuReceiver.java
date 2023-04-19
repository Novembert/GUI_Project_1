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

    public void initSaveState() {
        String saveName;
        do {
            saveName = Utilities.handleUserRequiredInput("Podaj nazwę zapisu: ");
        } while (this.saves.findIfSaveExists(saveName));


        this.runSaveState(saveName);
    }

    public void runSaveState(String saveName) {
        this.saves.createNewSave(saveName, stationsMap, carsCollection, trainsCollection, trainCarsMap);
    }

    public void initReadState() {
        List<String> savesNames = this.saves.listExistingSaves();
        String saveName = Utilities.handleUserRequiredStringListInput("Wybierz zapis (podaj odpowiadającą mu liczbę): ", savesNames);

        this.runReadState(saveName);
    }

    public void runReadState(String saveName) {
        this.saves.readSave(saveName);
    }

    public void initOverrideState() {
        List<String> savesNames = this.saves.listExistingSaves();
        String saveName = Utilities.handleUserRequiredStringListInput("Wybierz zapis (podaj odpowiadającą mu liczbę): ", savesNames);

        this.runOverrideState(saveName);
    }

    public void runOverrideState(String saveName) {
        this.saves.overrideSave(saveName, stationsMap, carsCollection, trainsCollection, trainCarsMap);
    }
}
