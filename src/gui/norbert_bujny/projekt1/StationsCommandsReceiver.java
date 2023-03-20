package gui.norbert_bujny.projekt1;

import java.util.List;

public class StationsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;

    public StationsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
    }

    public void initializeCreateStation() {
//        TODO maybe move it to a builder class?
        String name = Utilities.handleUserRequiredInput("Podaj nazwę: ");
        String code = Utilities.handleUserRequiredInput("Podaj kod: ");

        Station newStation = new Station(name, code);

        List<String> connectionsList = Utilities.handleUserListInput("[OPCJONALNIE]\nPodaj listę kodów miast, z którymi Twoja stacja ma być połączona.\nLista musi być rozdzielona przecinkami.");

        if (connectionsList.size() > 0) {
            this.stationsMap.addStationToList(newStation, connectionsList);
        } else {
            this.stationsMap.addStationToList(newStation);
        }
    }

    public void initializeDeleteStation() {
        try {
            this.stationsMap.removeStationFromList(Utilities.handleUserRequiredInput("Podaj kod miasta: "));
        } catch (StationNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
