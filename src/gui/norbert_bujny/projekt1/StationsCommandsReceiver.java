package gui.norbert_bujny.projekt1;

import java.util.List;

public class StationsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;

    public StationsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
    }

    public void initializeCreateStation() {
        String name = Utilities.handleUserRequiredInput("Podaj nazwę: ");
        String code = Utilities.handleUserRequiredInput("Podaj kod: ");

        Station newStation = new Station(name, code);

        List<String> connectionsList = Utilities.handleUserListInput("[OPCJONALNIE]\nPodaj listę kodów stacji, z którymi Twoja stacja ma być połączona.\nLista musi być rozdzielona przecinkami.");

        if (connectionsList.size() > 0) {
            int addedConnectionsCount = this.stationsMap.addStationToList(newStation, connectionsList);
            System.out.println("Dodano stację oraz " + addedConnectionsCount + " " + Utilities.getCorrectSingularOrPluralForm(addedConnectionsCount, "połączenie", "połączenia", "połączeń"));
        } else {
            this.stationsMap.addStationToList(newStation);
            System.out.println("Dodano stację");
        }
    }

    public void initializeDeleteStation() {
        try {
            this.stationsMap.removeStationFromList(Utilities.handleUserRequiredInput("Podaj kod stacji: "));
            System.out.println("Usunięto stację");
        } catch (StationNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
