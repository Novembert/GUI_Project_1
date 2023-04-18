package gui.norbert_bujny.projekt1;

import java.util.List;

public class StationsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;

    public StationsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
    }

    public void printStationsList() {
        System.out.println(this.stationsMap.getStationsList());
    }

    public void initializeCreateStation() {
        String name = Utilities.handleUserRequiredInput("Podaj nazwę: ");
        String code = Utilities.handleUserRequiredInput("Podaj kod: ");

        List<String> connectionsList = Utilities.handleUserListInput("[OPCJONALNIE]\nPodaj listę kodów stacji, z którymi Twoja stacja ma być połączona.\nLista musi być rozdzielona przecinkami.");

        if (connectionsList.size() > 0) {
            this.runCreateStation(name, code, connectionsList);
        } else {
            this.runCreateStation(name, code);
        }
    }

    public void runCreateStation(String name, String code, List<String> connectionsList) {
        Station newStation = new Station(name, code);

        int addedConnectionsCount = this.stationsMap.addStationToList(newStation, connectionsList);
        System.out.println("Dodano stację oraz " + addedConnectionsCount + " " + Utilities.getCorrectSingularOrPluralForm(addedConnectionsCount, "połączenie", "połączenia", "połączeń"));
    }

    public void runCreateStation(String name, String code) {
        Station newStation = new Station(name, code);

        this.stationsMap.addStationToList(newStation);
        System.out.println("Dodano stację");
    }

    public void initializeDeleteStation() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji: ");

        this.runDeleteStation(code);
    }

    public void runDeleteStation(String code) {
        try {
            this.stationsMap.removeStationFromList(code);
            System.out.println("Usunięto stację");
        } catch (StationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
